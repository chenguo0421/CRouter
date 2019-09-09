package cn.com.cg.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import cn.com.cg.router.manager.params.RouterParamsManager
import cn.com.cg.router.manager.path.RouterBeanManager

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/8/27 20:22
 */
open abstract class BaseActivity : AppCompatActivity() {

    open var callBackMethodID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RouterBeanManager.getInstance().registerAct(this)
        callBackMethodID = intent.getStringExtra(RouterParamsManager.METHODCALLBACKID)
    }

    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }

    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction{replace(frameId, fragment)}
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) = beginTransaction().func().commit()

    public override fun onDestroy() {
        RouterBeanManager.getInstance().unRegisterAct(this)
        super.onDestroy()
    }
}