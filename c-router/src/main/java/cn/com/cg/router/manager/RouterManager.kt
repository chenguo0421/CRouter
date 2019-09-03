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
        var METHODCALLBACKID:String = "METHOD_CALLBACKID"
        private @Volatile var Instance: SoftReference<RouterManager>? = null
        private @Volatile var intent: Intent? = null
        private @Volatile var action: String? = null
        private @Volatile var callBackID: String? = null
        private @Volatile var clsName: String? = null
        private @Volatile var enterAnim: Int? = 0
        private @Volatile var outerAnim: Int? = 0
        private @Volatile var view: View? = null
        private @Volatile var context: SoftReference<Context>? = null
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
        RouterManager.view = view
        return getInstance()
    }


    /**
     * 设置页面切换动画
     */
    fun anim(enterAnim:Int,outerAnim:Int): RouterManager{
        RouterManager.enterAnim = enterAnim
        RouterManager.outerAnim = outerAnim
        return getInstance()
    }


    /**
     * 配置Context
     */
    fun with(context: Context): RouterManager {
        RouterManager.context = SoftReference(context)
        return getInstance()

    }


    /**
     * 自定义Intent，启动方式由使用者去设置
     */
    fun intent(intent:Intent): RouterManager{
        RouterManager.intent = intent
        return getInstance()
    }


    /**
     * 配置路由
     */
    fun action(action: String): RouterManager {
        RouterManager.action = action
        return getInstance()
    }



    /**
     * 请求方设置回调
     */
    fun setCallBack(callBack:RouterCallBack): RouterManager {
        RouterManager.callBackID = RouterCallBackManager.getInstance().put(callBack)
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
        jumpActivity(RouterManager.context!!.get()!!,intent)
        //发生一次请求，清除脏数据
        clearCatchData()
    }

    /**
     * 清理用完的脏数据
     */
    private fun clearCatchData() {
        RouterManager.context = null
        RouterManager.action = null
        RouterManager.intent = null
        RouterManager.callBackID = null
        RouterManager.clsName = null
        RouterManager.enterAnim = 0
        RouterManager.outerAnim = 0
        RouterManager.view = null
    }

    /**
     * 调用方法
     */
    fun callMethod(vararg params: Any): Any? {
        if (RouterManager.action == null) {
            throw Exception("please with action first!")
        }
        val clsPath: String? = RouterPathManager.getInstance()
            .getClassPathByMethodPath(RouterManager.context!!.get()!!, RouterManager.action!!)
        if (clsPath != null) {
            return RouterMethodManager.getInstance().invoke(clsPath, action, *params)
        }
        return null
    }

    /**
     * 页面切换
     */
    private fun jumpActivity(context: Context, intent: Intent) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP && context is Activity && RouterManager.view != null) {
            val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(context, RouterManager.view!!, RouterManager.view!!.transitionName!!)
            ActivityCompat.startActivity(context, intent, compat.toBundle())//复制代码
        } else {
            context.startActivity(intent)
            if (context is Activity && enterAnim != null && outerAnim != null) {
                context.overridePendingTransition(enterAnim!!, outerAnim!!)
            }
        }
    }

    /**
     * 构建构建Intent
     */
    private fun createIntent(): Intent {
        if (RouterManager.context == null){
            throw Exception("please with context first!")
        }
        if (RouterManager.action == null){
            throw Exception("please with action first!")
        }
        if (RouterManager.intent == null){
            RouterManager.intent = Intent()
        }

        var clz: Class<*>? = RouterPathManager.getInstance().getClassFromRouterPath(RouterManager.context!!.get()!!, RouterManager.action!!)
        RouterManager.intent?.setClass(RouterManager.context!!.get()!!, clz!!)
        if (RouterManager.callBackID != null) {
            RouterManager.intent?.putExtra(METHODCALLBACKID, callBackID)
        }
        return RouterManager.intent!!
    }


}
