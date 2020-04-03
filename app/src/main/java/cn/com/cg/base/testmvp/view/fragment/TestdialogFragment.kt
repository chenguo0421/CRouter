package cn.com.cg.base.testmvp.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import cn.com.cg.base.BaseDialogFragment
import cn.com.cg.base.R
import cn.com.cg.base.testmvp.contract.TestdialogFMContract
import cn.com.cg.base.testmvp.presenter.TestdialogFMPresenter
import cn.com.cg.router.annotation.CRouter

/**
 *  author : ChenGuo
 *  date : 2019-12-13 15:22:47
 *  description : { 请添加该类的描述 }
 */
@CRouter(path = "TestdialogFragment")
class TestdialogFragment :TestdialogFMContract.IView, BaseDialogFragment<TestdialogFMContract.IView, TestdialogFMContract.IPresenter<TestdialogFMContract.IView>>() {
    private lateinit var bundle: Bundle
    private lateinit var mPresenter: TestdialogFMContract.IPresenter<TestdialogFMContract.IView>

    override fun createPresenter(): TestdialogFMContract.IPresenter<TestdialogFMContract.IView> {
        mPresenter = TestdialogFMPresenter()
        return mPresenter
    }

    override fun getInstance(): BaseDialogFragment<TestdialogFMContract.IView, TestdialogFMContract.IPresenter<TestdialogFMContract.IView>> {
        synchronized(TestdialogFragment::class){
            return TestdialogFragment()
        }
    }

    override fun fragmentIOAnimation(): Int {
        return R.style.RightAnimation
    }

    override fun getBaseActivity(): Context {
        return activity!!
    }

    override fun setBundleExtra(bundle: Bundle) {
        this.bundle = bundle
    }

    override fun createView(): TestdialogFMContract.IView {
        return this
    }

    override fun initLayoutId(): Int {
        return R.layout.fragment_dialog_test
    }

    override fun initData() {

    }

    override fun initListener() {

    }

    override fun setDialogWidth(): Int {
        return ViewGroup.LayoutParams.MATCH_PARENT
    }

    override fun setDialogHeight(): Int {
        return ViewGroup.LayoutParams.MATCH_PARENT
    }

    override fun setOutSideAlpha(): Float? {
        return 1f
    }

    override fun setGravity(): Int {
        return Gravity.CENTER
    }
}