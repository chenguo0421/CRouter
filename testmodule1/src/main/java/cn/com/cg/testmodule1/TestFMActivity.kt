package cn.com.cg.testmodule1

import android.os.Bundle
import android.view.View
import cn.com.cg.base.BaseActivity
import cn.com.cg.base.BaseFragment
import cn.com.cg.router.annotation.CRouter
import cn.com.cg.router.manager.RouterManager
import kotlinx.android.synthetic.main.fm_activity.*

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/9/9 14:59
 */
@CRouter("TestFMActivity")
class TestFMActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.showBtn1 -> replaceFragment(fm1!!,R.id.fm1)
            R.id.showBtn2 -> replaceFragment(fm2!!,R.id.fm2)
            R.id.showBtn3 -> callMyFragmentMethodByTag("tag1")
            R.id.showBtn4 -> callMyFragmentMethodByTag("tag2")
        }
    }

    private fun callMyFragmentMethodByTag(tag: String) {
        var msg:String? = null
        when(tag){
            "tag1" -> {
                msg = "我是第一个对象"
            }
            "tag2" -> {
                msg = "我是第二个对象"
            }
        }
        RouterManager.getInstance()
            .with(this)
            .fragmentTag(tag)
            .action("/MyFragment/changeText")
            .callMethod(msg!!)
    }

    var fm1: BaseFragment? = null
    var fm2: BaseFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fm_activity)
        fm1 = RouterManager.getInstance().with(this).fragmentTag("tag1").action("MyFragment").navigation() as BaseFragment?
        fm2 = RouterManager.getInstance().with(this).fragmentTag("tag2").action("MyFragment").navigation() as BaseFragment?
        showBtn1.setOnClickListener(this)
        showBtn2.setOnClickListener(this)
        showBtn3.setOnClickListener(this)
        showBtn4.setOnClickListener(this)
    }
}