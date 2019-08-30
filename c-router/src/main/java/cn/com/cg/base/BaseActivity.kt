package cn.com.cg.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.com.cg.router.manager.RouterManager

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/8/27 20:22
 */
open class BaseActivity : AppCompatActivity() {

    open var callBackMethodID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callBackMethodID = intent.getStringExtra(RouterManager.METHODCALLBACKID)
    }
}