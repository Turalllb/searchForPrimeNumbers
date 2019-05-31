package com.turalllb.primeNumbers.ui.mainActivity

class NullObject: MainView {

    init {
        try {
            throw NullPointerException("View is null")
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }


    override fun showProgress() {}

    override fun hideProgress() {}

    override fun refreshData(primeNumbers: List<Long>, sumPrimeNumbers: Long, timeElapsed: Double) {}

    override fun calculateBtSetEnabled(state: Boolean) {}
}