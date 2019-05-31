package com.turalllb.primeNumbers.ui.base

import com.turalllb.primeNumbers.ui.mainActivity.NullObject

open class BasePresenter<V : BaseView> {

    protected var view: V = NullObject() as V


    fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        view = NullObject() as V
    }

}