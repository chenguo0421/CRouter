package cn.com.cg.base.testmvp.contract

import cn.com.cg.mvp.base.BaseModel
import cn.com.cg.mvp.base.BasePresenter
import cn.com.cg.mvp.base.intf.BaseView

/**
 *  author : ChenGuo
 *  date : 2019-12-13 15:22:47
 *  description : { 请添加该类的描述 }
 */
class TestdialogFMContract {

    public interface IView:BaseView{

    }


    public abstract class IPresenter<T> : BasePresenter<T>() {

    }


    public abstract class IModel:BaseModel(){

    }
}