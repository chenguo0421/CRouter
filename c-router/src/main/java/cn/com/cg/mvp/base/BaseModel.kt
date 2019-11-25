package cn.com.cg.base

import android.content.Context
import cn.com.cg.mvp.observelistener.ObserveResponseListener
import cn.com.cg.mvp.observelistener.ProgressObserver

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/11/25 15:05
 */
abstract class BaseModel {

    fun subscribe(
        context: Context,
        observable: Observable<*>,
        listener: ObserveResponseListener,
        transformer: ObservableTransformer<Any,Any>
    ) {
        val observer = ProgressObserver(context, listener)
        observable.compose(transformer)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}