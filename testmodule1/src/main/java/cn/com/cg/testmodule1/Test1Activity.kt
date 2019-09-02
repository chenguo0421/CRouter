package cn.com.cg.testmodule1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import cn.com.cg.base.BaseActivity
import cn.com.cg.base.CRouterBaseActivity
import cn.com.cg.router.annotation.CMethod
import cn.com.cg.router.annotation.CRouter
import cn.com.cg.router.manager.RouterManager
import cn.com.cg.router.manager.intf.RouterCallBack
import kotlinx.android.synthetic.main.t1_activity.*
import java.util.*

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/8/27 17:04
 */

@CRouter("Test1Activity")
class Test1Activity : CRouterBaseActivity(), View.OnClickListener, RouterCallBack {

    /**
     * 通过接口方法回调
     */
    override fun onCallBack(data: Any) {
        var msg = data as String
        back_tv.text = msg
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn1 -> gotoT2()
        }
    }


    /**
     * 通过注解反射跳转到Test2Activity
     */
    private fun gotoT2() {
        RouterManager.getInstance().with(this).action("Test2Activity").setCallBack(this).navigation()
    }


    /**
     * 通过注解反射回调
     */
    @CMethod("/Test1Activity/onT2CallBack")
    fun onT2InvokeCallBack(vararg params:Any): String {
        back_tv.text = params[0].toString()
        return "hello t2,i am t1"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.t1_activity)
        btn1.setOnClickListener(this)
    }
}


