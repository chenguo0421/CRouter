package cn.com.cg.testmodule2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.cg.base.BaseFragment
import cn.com.cg.router.annotation.CRouter
import cn.com.cg.testmodule2.R

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/9/9 10:35
 */
@CRouter("PortalFragment")
class PortalFragment : BaseFragment(){

    override fun getInstance(): BaseFragment {
        synchronized(PortalFragment::class){
            return PortalFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fm_portal,container,false)
    }

}