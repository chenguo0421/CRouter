package cn.com.cg.testmvp.view

import android.content.Context
import cn.com.cg.base.BaseActivity
import cn.com.cg.testmvp.contract.TestContract
import cn.com.cg.testmvp.presenter.TestPresenter

class TestActivity : TestContract.IView, BaseActivity<TestContract.IView, TestContract.IPresenter<TestContract.IView>>() {

    private lateinit var mPresenter: TestContract.IPresenter<TestContract.IView>

    override fun createPresenter(): TestContract.IPresenter<TestContract.IView> {
        mPresenter = TestPresenter()
        return mPresenter
    }

    override fun createView(): TestContract.IView {
        return this
    }

    override fun initLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBaseActivity(): Context {
        return this
    }
}