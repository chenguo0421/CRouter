package cn.com.cg.testmodule1

import android.content.Intent
import android.os.Bundle
import android.view.View
import cn.com.cg.base.BaseActivity
import cn.com.cg.router.annotation.CMethod
import cn.com.cg.router.annotation.CRouter
import cn.com.cg.router.manager.RouterManager
import cn.com.cg.router.manager.intf.RouterCallBack
import kotlinx.android.synthetic.main.t1_activity.*

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/8/27 17:04
 */

@CRouter(path = "Test1Activity")
class Test1Activity : BaseActivity(), View.OnClickListener, RouterCallBack {

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
            R.id.btn2 -> gotoFMActivity()
        }
    }




    private fun gotoFMActivity() {
        RouterManager.getInstance()
            .with(this)
            .action("TestFMActivity")
            .navigation()
    }


    /**
     * 通过注解反射跳转到Test2Activity
     */
    private fun gotoT2() {
        val intent = Intent()
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        RouterManager.getInstance()
            .with(this)
            .sharedElement(img)
            .anim(R.anim.slide_in_left,R.anim.slide_out_right)
            .action("Test2Activity")
            .intent(intent)
            .setCallBack(this)
            .navigation()
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
        btn2.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}


