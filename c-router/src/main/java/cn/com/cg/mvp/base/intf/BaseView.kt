package cn.com.cg.mvp.base.intf

import cn.com.cg.base.BaseActivity
import cn.com.cg.base.BasePresenter

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/11/25 14:56
 */
interface BaseView {
    public fun getBaseActivity():BaseActivity<BaseView,BasePresenter<BaseView>>
}