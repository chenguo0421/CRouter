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

    /**
     * Activity实例
     */
    private var actMap:HashMap<String,SoftReference<CRouterBaseActivity>>? = null
    /**
     * Fragment实例
     */
    private var fmMap:HashMap<String,SoftReference<CRouterBaseFragment>>? = null

    /**
     * 其他工具类实例
     */
    private var utilMap: HashMap<String, SoftReference<Any?>>? = null


    fun registerAct(obj: CRouterBaseActivity) {
        actMap?.put(obj::class.qualifiedName!!,SoftReference(obj))
    }

    fun registerFM(obj: CRouterBaseFragment) {
        fmMap?.put(obj::class.qualifiedName!!,SoftReference(obj))
    }

    fun unRegisterAct(obj: CRouterBaseActivity) {
        actMap?.remove(obj::class.qualifiedName!!)
    }

    fun unRegisterFM(obj: CRouterBaseFragment) {
        fmMap?.remove(obj::class.qualifiedName!!)
    }

    fun getActBean(clsPath:String):CRouterBaseActivity?{
        return actMap!![clsPath]?.get()
    }

    fun getFMBean(clsPath:String):CRouterBaseFragment?{
        return fmMap!![clsPath]?.get()
    }

    fun getOtherBean(clzPath: String): Any? {
        if (utilMap!![clzPath]==null){
            utilMap!![clzPath] = SoftReference(Class.forName(clzPath).newInstance())
        }
        return utilMap!![clzPath]?.get()
    }

    private constructor(){
        if (actMap == null) {
            actMap = HashMap()
        }
        if (fmMap == null) {
            fmMap = HashMap()
        }
        if (utilMap == null) {
            utilMap = HashMap()
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