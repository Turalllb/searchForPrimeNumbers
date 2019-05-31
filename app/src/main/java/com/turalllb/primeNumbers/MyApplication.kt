package com.turalllb.primeNumbers.ui

import android.app.Application
import com.turalllb.primeNumbers.ui.mainActivity.MainView
import com.turalllb.primeNumbers.ui.mainActivity.Presenter




class MyApplication : Application() {
    companion object {
        var INSTANCE: Presenter<MainView> = Presenter()
    }


}