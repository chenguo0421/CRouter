package cn.com.cg.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import cn.com.cg.router.manager.path.RouterBeanManager

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/8/27 20:41
 */
open abstract class BaseFragment : Fragment(){

    open var fragmentTag:String? = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RouterBeanManager.getInstance().registerFM(this)
    }


    abstract fun getInstance():BaseFragment


    override fun onDestroy() {
        RouterBeanManager.getInstance().unRegisterFM(this)
        super.onDestroy()
    }
}