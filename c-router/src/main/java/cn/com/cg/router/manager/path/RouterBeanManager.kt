package cn.com.cg.router.manager.path

import cn.com.cg.base.BaseActivity
import cn.com.cg.base.CRouterBaseActivity
import cn.com.cg.base.CRouterBaseFragment
import java.lang.ref.SoftReference

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/8/30 14:54
 */
class RouterBeanManager{

    private var actMap:HashMap<String,SoftReference<CRouterBaseActivity>>? = null
    private var fmMap:HashMap<String,SoftReference<CRouterBaseFragment>>? = null

    fun registerAct(obj: CRouterBaseActivity) {
        actMap?.put(obj::class.qualifiedName!!,SoftReference(obj))
    }

    fun registerFM(obj: CRouterBaseFragment) {
        fmMap?.put(obj::class.qualifiedName!!,SoftReference(obj))
    }

    fun getActBean(clsPath:String):CRouterBaseActivity?{
        return actMap!![clsPath]?.get()
    }

    fun getFMBean(clsPath:String):CRouterBaseFragment?{
        return fmMap!![clsPath]?.get()
    }

    private constructor(){
        if (actMap == null) {
            actMap = HashMap()
        }
        if (fmMap == null) {
            fmMap = HashMap()
        }
    }

    /**
     * 单例
     */
    companion object {
        @Volatile
        private var instance: RouterBeanManager? = null
        fun getInstance(): RouterBeanManager {
            if (instance == null) {
                synchronized(RouterPathManager::class.java){
                    if (instance == null) {
                        instance = RouterBeanManager()
                    }
                }
            }
            return instance!!
        }

    }
}