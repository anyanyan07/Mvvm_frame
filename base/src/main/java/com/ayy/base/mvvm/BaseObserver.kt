package com.ayy.base.mvvm

import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable


class BaseObserver<T>(var callback: Callback<T>) : Observer<T> {
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable?) {

    }

    override fun onNext(t: T) {
        callback.onSuccess(t, false)
    }

    override fun onError(e: Throwable) {
        callback.onFail(e, false)
    }

}