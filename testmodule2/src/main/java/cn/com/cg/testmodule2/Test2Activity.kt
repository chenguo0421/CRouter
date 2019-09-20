package cn.com.cg.testmodule2

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import cn.com.cg.base.BaseActivity
import cn.com.cg.router.annotation.CRouter
import cn.com.cg.router.manager.RouterManager
import kotlinx.android.synthetic.main.t2_activity.*

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/8/27 17:05
 */
@CRouter("Test2Activity")
class Test2Activity : BaseActivity(){


    @RequiresApi(Build.VERSION_CODES.KITKAT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.t2_activity)
        btn1.setOnClickListener {
            when(it?.id){
                R.id.btn1 -> resultData()
            }
        }

        btn2.setOnClickListener {
            callMethodByMethodPath()
        }

        btn3.setOnClickListener {
            callUtilsMethodByMethodPath()
        }

    }


    /**
     * 通过方法注解向Test1Activity回调数据
     */
    private fun callMethodByMethodPath() {
        val msg = RouterManager.getInstance()
            .with(this)
            .action("/Test1Activity/onT2CallBack")
            .callMethod("hello word")
        tv2.text = msg.toString()
    }

    /**
     * 通过方法注解向Test1Activity回调数据
     */
    private fun callUtilsMethodByMethodPath() {
        val msg = RouterManager.getInstance()
            .with(this)
            .action("/MyUtils/testCallUtilsMethod")
            .callMethod("hello word")
        tv2.text = msg.toString()
    }


    /**
     * 通过接口ID向Test1Activity回调数据
     */
    private fun resultData() {
        var callbackData: String = "我来自第二页"
        RouterManager.getInstance()
            .onCallBack(callBackMethodID,callbackData)
        RouterManager.getInstance().with(this).finish()
//        overridePendingTransition(0,0)
    }


}