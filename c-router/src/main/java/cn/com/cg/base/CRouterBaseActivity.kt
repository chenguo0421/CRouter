package cn.com.cg.base

import android.os.Bundle
import cn.com.cg.router.manager.path.RouterBeanManager

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/8/30 14:52
 */
open class CRouterBaseActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RouterBeanManager.getInstance().registerAct(this)
    }
}