package cn.com.cg.router.manager.method

import cn.com.cg.router.annotation.CMethod
import cn.com.cg.router.manager.path.RouterBeanManager
import java.lang.Exception
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.*
import kotlin.collections.HashMap

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/8/30 15:27
 */
class RouterMethodManager {



    fun invoke(clzPath: String?, action: String?,vararg params:Any) {
        when {
            invokeActivity(clzPath,action,params) -> return
            invokeFragment(clzPath,action,params) -> return
            else -> invokeOthers(clzPath,action,params)
        }
    }


    private fun invokeOthers(clzPath: String?, action: String?,vararg params:Any) {
        var tempMap:HashMap<String,Method> = HashMap()
        try {
            val cls = Class.forName(clzPath!!)
            val methods = cls.declaredMethods
            for (method in methods) {
                val cMethod = method.getAnnotation(CMethod::class.java)
                if (cMethod != null) {
                    tempMap[cMethod.path] = method
                }
            }
            if (tempMap[action] != null) {
                tempMap[action]?.invoke(cls,*params)
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun invokeFragment(clzPath: String?, action: String?,vararg params:Any): Boolean {
        var bean = RouterBeanManager.getInstance().getFMBean(clzPath!!)
        if (bean != null) {
            val tempMap:HashMap<String,Method> = HashMap()
            try {
                var cls = Class.forName(clzPath)
                val methods = cls.declaredMethods
                for (method in methods) {
                    val cMethod = method.getAnnotation(CMethod::class.java)
                    if (cMethod != null) {
                        tempMap[cMethod.path] = method
                    }
                }
                if (tempMap[action] != null) {
                    tempMap[action]?.invoke(bean,*params)
                }
                return true
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
      return false
    }

    private fun invokeActivity(clzPath: String?, action: String?,vararg params:Any): Boolean {
        var bean = RouterBeanManager.getInstance().getActBean(clzPath!!)
        if (bean != null) {
            val tempMap:HashMap<String,Method> = HashMap()
            try {
                var cls = Class.forName(clzPath)
                val methods = cls.declaredMethods
                for (method in methods) {
                    val cMethod = method.getAnnotation(CMethod::class.java)
                    if (cMethod != null) {
                        tempMap[cMethod.path] = method
                    }
                }
                if (tempMap[action] != null) {
                    tempMap[action]?.invoke(bean,*params)
                }
                return true
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        return false
    }


    /**
     * 单例
     */
    companion object {
        @Volatile
        private var instance: RouterMethodManager? = null
        fun getInstance(): RouterMethodManager {
            if (instance == null) {
                synchronized(RouterMethodManager::class.java){
                    if (instance == null) {
                        instance = RouterMethodManager()
                    }
                }
            }
            return instance!!
        }

    }
}