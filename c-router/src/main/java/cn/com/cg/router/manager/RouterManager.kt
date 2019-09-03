package cn.com.cg.router.manager

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.animation.Animation
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import cn.com.cg.router.R
import cn.com.cg.router.manager.callback.RouterCallBackManager
import cn.com.cg.router.manager.intf.RouterCallBack
import cn.com.cg.router.manager.method.RouterMethodManager
import cn.com.cg.router.manager.params.RouterParamsManager
import cn.com.cg.router.manager.path.RouterPathManager
import java.lang.Exception
import java.lang.ref.SoftReference
import java.util.*

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/8/27 18:15
 */
class RouterManager private constructor(){

    var TAG : String? = "RouterManager"
    /**
     *单例
     */
    companion object {

        private @Volatile var Instance: SoftReference<RouterManager>? = null
        fun getInstance(): RouterManager{
            if (Instance == null) {
                synchronized(this) {
                    if (Instance == null) {
                        Instance = SoftReference(RouterManager())
                    }
                }
            }
            return Instance!!.get()!!
        }
    }

    /**
     * 共享元素
     */
    fun sharedElement(view:View): RouterManager{
        RouterParamsManager.getInstance().sharedElement(view)
        return getInstance()
    }


    /**
     * 设置页面切换动画
     */
    fun anim(enterAnim:Int,outerAnim:Int): RouterManager{
        RouterParamsManager.getInstance().anim(enterAnim,outerAnim)
        return getInstance()
    }


    /**
     * 配置Context
     */
    fun with(context: Context): RouterManager {
        RouterParamsManager.getInstance().with(context)
        return getInstance()

    }


    /**
     * 自定义Intent，启动方式由使用者去设置
     */
    fun intent(intent:Intent): RouterManager{
        RouterParamsManager.getInstance().intent(intent)
        return getInstance()
    }


    /**
     * 配置路由
     */
    fun action(action: String): RouterManager {
        RouterParamsManager.getInstance().action(action)
        return getInstance()
    }



    /**
     * 请求方设置回调
     */
    fun setCallBack(callBack:RouterCallBack): RouterManager {
        RouterParamsManager.getInstance().setCallBack(callBack)
        return getInstance()
    }


    /**
     * 目标方调用回调
     */
    fun onCallBack(callBackID:String,data:Any){
        var callBack = RouterCallBackManager.getInstance().get(callBackID)
        callBack?.onCallBack(data)
        //完成一次回调，清除脏数据
        clearCatchData()
    }

    /**
     * 带Intent的跳转，intent中可指定启动模式，指定bundle等等
     */
    fun navigation() {
        var intent = createIntent()
        jumpActivity(RouterParamsManager.context!!.get()!!,intent)
        //发生一次请求，清除脏数据
        clearCatchData()
    }

    /**
     * 清理用完的脏数据
     */
    private fun clearCatchData() {
        RouterParamsManager.getInstance().clearCatchData()
    }

    /**
     * 调用方法
     */
    fun callMethod(vararg params: Any): Any? {
        if (RouterParamsManager.action == null) {
            throw Exception("please with action first!")
        }
        val clsPath: String? = RouterPathManager.getInstance()
            .getClassPathByMethodPath(RouterParamsManager.context!!.get()!!, RouterParamsManager.action!!)
        if (clsPath != null) {
            return RouterMethodManager.getInstance().invoke(clsPath, RouterParamsManager.action, *params)
        }
        return null
    }

    /**
     * 页面切换
     */
    private fun jumpActivity(context: Context, intent: Intent) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP && context is Activity && RouterParamsManager.view?.get() != null) {
            val compat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(context
                    , RouterParamsManager.view!!.get()!!
                    , RouterParamsManager.view!!.get()!!.transitionName!!)
            ActivityCompat.startActivity(context, intent, compat.toBundle())//复制代码
        } else {
            context.startActivity(intent)
            if (context is Activity && RouterParamsManager.enterAnim != 0 && RouterParamsManager.outerAnim != 0) {
                context.overridePendingTransition(RouterParamsManager.enterAnim!!, RouterParamsManager.outerAnim!!)
            }
        }
    }

    /**
     * 构建构建Intent
     */
    private fun createIntent(): Intent {
        if (RouterParamsManager.context == null){
            throw Exception("please with context first!")
        }
        if (RouterParamsManager.action == null){
            throw Exception("please with action first!")
        }
        if (RouterParamsManager.intent == null){
            RouterParamsManager.intent = Intent()
        }

        var clz: Class<*>? = RouterPathManager.getInstance().getClassFromRouterPath(RouterParamsManager.context!!.get()!!, RouterParamsManager.action!!)
        RouterParamsManager.intent?.setClass(RouterParamsManager.context!!.get()!!, clz!!)
        if (RouterParamsManager.callBackID != null) {
            RouterParamsManager.intent?.putExtra(RouterParamsManager.METHODCALLBACKID, RouterParamsManager.callBackID)
        }
        return RouterParamsManager.intent!!
    }


    fun finish() {
        if (RouterParamsManager.context == null){
            throw Exception("please with context first!")
        }
        if (RouterParamsManager.context?.get() is Activity){
            if (RouterParamsManager.enterAnim != 0 && RouterParamsManager.outerAnim != 0) {
                (RouterParamsManager.context?.get() as Activity).finish()
                (RouterParamsManager.context?.get() as Activity).overridePendingTransition(RouterParamsManager.enterAnim!!, RouterParamsManager.outerAnim!!)
            }else{
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
                    (RouterParamsManager.context?.get() as Activity).finishAfterTransition()
                }else{
                    (RouterParamsManager.context?.get() as Activity).finish()
                }
            }
        }
    }


}
