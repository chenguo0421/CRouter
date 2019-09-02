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



    fun invoke(clzPath: String?, action: String?,vararg params:Any): Any? {

        when {
            invokeActivity(clzPath).size > 0 -> {
                return invokeMethod(invokeActivity(clzPath), RouterBeanManager.getInstance().getActBean(clzPath!!), action, params)
            }
            invokeFragment(clzPath).size > 0 -> {
                return invokeMethod(invokeFragment(clzPath), RouterBeanManager.getInstance().getFMBean(clzPath!!), action, params)
            }
            invokeOthers(clzPath).size > 0 -> {
                return invokeMethod(invokeOthers(clzPath), RouterBeanManager.getInstance().getOtherBean(clzPath!!), action, params)
            }
        }
        return null
    }

    private fun invokeMethod(tempMap:HashMap<String,Method>, cls: Any?, action: String?, vararg params:Any): Any? {
        if (tempMap[action] != null) {
            return tempMap[action]?.invoke(cls,*params)
        }
        return null
    }


    private fun invokeOthers(clzPath: String?):HashMap<String,Method> {
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
        }catch (e:Exception){
            e.printStackTrace()
        }
        return tempMap
    }

    private fun invokeFragment(clzPath: String?): HashMap<String,Method> {
        var bean = RouterBeanManager.getInstance().getFMBean(clzPath!!)
        val tempMap:HashMap<String,Method> = HashMap()
        if (bean != null) {
            try {
                var cls = Class.forName(clzPath)
                val methods = cls.declaredMethods
                for (method in methods) {
                    val cMethod = method.getAnnotation(CMethod::class.java)
                    if (cMethod != null) {
                        tempMap[cMethod.path] = method
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
      return tempMap
    }

    private fun invokeActivity(clzPath: String?): HashMap<String,Method> {
        var bean = RouterBeanManager.getInstance().getActBean(clzPath!!)
        val tempMap:HashMap<String,Method> = HashMap()
        if (bean != null) {
            try {
                var cls = Class.forName(clzPath)
                val methods = cls.declaredMethods
                for (method in methods) {
                    val cMethod = method.getAnnotation(CMethod::class.java)
                    if (cMethod != null) {
                        tempMap[cMethod.path] = method
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        return tempMap
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