package cn.com.cg.testmvp.view

import android.content.Context
import android.view.View
import cn.com.cg.base.BaseFragment
import cn.com.cg.testmvp.contract.TestContract

class TestFragment :TestContract.IView, BaseFragment<TestContract.IView, TestContract.IPresenter<TestContract.IView>>() {
    override fun getBaseActivity(): Context {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createPresenter(): TestContract.IPresenter<TestContract.IView> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createView(): TestContract.IView {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView(view: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getInstance(): BaseFragment<TestContract.IView, TestContract.IPresenter<TestContract.IView>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}