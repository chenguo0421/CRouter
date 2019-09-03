package cn.com.cg.router.manager.params

import android.content.Context
import android.content.Intent
import android.view.View
import cn.com.cg.router.manager.callback.RouterCallBackManager
import cn.com.cg.router.manager.intf.RouterCallBack
import java.lang.ref.SoftReference

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/9/3 19:13
 */
open class RouterParamsManager {

    companion object{
        var METHODCALLBACKID:String = "METHOD_CALLBACKID"
        @Volatile var intent: Intent? = null
        @Volatile var action: String? = null
        @Volatile var callBackID: String? = null
        @Volatile var clsName: String? = null
        @Volatile var enterAnim: Int? = 0
        @Volatile var outerAnim: Int? = 0
        @Volatile var view: SoftReference<View>? = null
        @Volatile var context: SoftReference<Context>? = null

        private @Volatile var Instance: SoftReference<RouterParamsManager>? = null
        fun getInstance(): RouterParamsManager{
            if (Instance == null) {
                synchronized(this) {
                    if (Instance == null) {
                        Instance = SoftReference(RouterParamsManager())
                    }
                }
            }
            return Instance!!.get()!!
        }
    }


    fun sharedElement(view: View) {
        RouterParamsManager.view = SoftReference(view)
    }

    fun anim(enterAnim: Int, outerAnim: Int) {
        RouterParamsManager.enterAnim = enterAnim
        RouterParamsManager.outerAnim = outerAnim
    }

    fun with(context: Context) {
        RouterParamsManager.context = SoftReference(context)
    }

    fun intent(intent: Intent) {
        RouterParamsManager.intent = intent
    }

    fun action(action: String) {
        RouterParamsManager.action = action
    }

    fun setCallBack(callBack: RouterCallBack) {
        RouterParamsManager.callBackID = RouterCallBackManager.getInstance().put(callBack)
    }

    fun clearCatchData() {
        RouterParamsManager.context = null
        RouterParamsManager.action = null
        RouterParamsManager.intent = null
        RouterParamsManager.callBackID = null
        RouterParamsManager.clsName = null
        RouterParamsManager.enterAnim = 0
        RouterParamsManager.outerAnim = 0
        RouterParamsManager.view = null
    }

}