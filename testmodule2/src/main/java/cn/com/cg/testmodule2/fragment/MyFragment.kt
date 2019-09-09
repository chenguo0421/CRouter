package cn.com.cg.testmodule2.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.cg.base.BaseFragment
import cn.com.cg.router.annotation.CMethod
import cn.com.cg.router.annotation.CRouter
import cn.com.cg.testmodule2.R
import kotlinx.android.synthetic.main.fm_my.*

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/9/9 10:34
 */
@CRouter("MyFragment")
class MyFragment : BaseFragment() {

    override fun getInstance(): BaseFragment {
        synchronized(MyFragment::class){
            return MyFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fm_my,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    @CMethod("/MyFragment/changeText")
    fun changeText(vararg params:Any){
        Log.e("changeText", "params = $params")
        tv.text = params[0].toString()

    }

}