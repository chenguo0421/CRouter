package cn.com.cg.base

import android.os.Bundle
import android.view.View
import cn.com.cg.router.manager.path.RouterBeanManager

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/8/30 14:52
 */
open class CRouterBaseFragment:BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RouterBeanManager.getInstance().registerFM(this)
    }

    override fun onDestroy() {
        RouterBeanManager.getInstance().unRegisterFM(this)
        super.onDestroy()
    }
}