package com.turalllb.primeNumbers.ui.mainActivity

import com.turalllb.primeNumbers.ui.base.BaseView

interface MainView : BaseView {

    fun showProgress()

    fun hideProgress()

    fun calculateBtSetEnabled(state: Boolean)

    fun refreshData(
        primeNumbers: List<Long>,
        sumPrimeNumbers: Long,
        timeElapsed: Double
    )

}