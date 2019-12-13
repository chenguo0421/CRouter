package cn.com.cg.base.testmvp.presenter

import cn.com.cg.base.testmvp.contract.TestdialogFMContract
import cn.com.cg.base.testmvp.model.TestdialogFMModel

/**
 *  author : ChenGuo
 *  date : 2019-12-13 15:22:47
 *  description : { 请添加该类的描述 }
 */
class TestdialogFMPresenter : TestdialogFMContract.IPresenter<TestdialogFMContract.IView>() {

    private var mModel:TestdialogFMModel? = null

    init {
        mModel = TestdialogFMModel()
    }
}