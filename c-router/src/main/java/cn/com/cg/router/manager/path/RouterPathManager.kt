package cn.com.cg.router.manager.path

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import cn.com.cg.router.bean.RouterBean
import cn.com.cg.router.utils.AnnotationUtils
import cn.com.cg.router.utils.RouterXmlParser
import java.lang.Exception
import java.lang.reflect.Method

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/8/27 20:25
 */
class RouterPathManager {

    /**
     * 类路由表，所有类路由可来这里查询
     */
    var routerMap: HashMap<String, RouterBean>? = null

    /**
     * 方法路由表，所有方法路由可来这里查询
     */
    var methodMap: HashMap<String, String>? = null

    private constructor()

    fun init(context:Context){
        if (routerMap == null) {
            routerMap = HashMap()
        }
        if (methodMap == null) {
            methodMap = HashMap()
        }
        RouterXmlParser.parseRouterMap(context,routerMap,methodMap)
    }

    fun getClassFromRouterPath(context:Context,routerPath: String):  Class<*>? {
        if (routerMap == null) {
            init(context)
        }
        var clsPath = routerMap?.get(routerPath)?.classPaths
        try {
            var cls = Class.forName(clsPath!!)
            return cls
        }catch (e:Exception){}
        return null
    }


    fun getClassNameByMethodPath(context: Context, action: String): String? {
        if (methodMap == null) {
            methodMap = RouterXmlParser.parseMethodMap(context)
        }
        return methodMap?.get(action)
    }


    companion object {
        @Volatile
        private var instance: RouterPathManager? = null
        fun getInstance(): RouterPathManager {
            if (instance == null) {
                synchronized(RouterPathManager::class.java){
                    if (instance == null) {
                        instance = RouterPathManager()
                    }
                }
            }
            return instance!!
        }

    }
}


