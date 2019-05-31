package com.turalllb.primeNumbers.ui.mainActivity

import com.turalllb.primeNumbers.ui.base.BasePresenter


class Presenter<V : MainView> : BasePresenter<V>() {
    internal val primeNumbers: MutableList<Long> = arrayListOf()
    internal var sumPrimeNumbers: Long = 0
    internal var timeElapsed: Double = 0.0
    internal var calculationBtState: Boolean = true
    internal var progressBarState: Boolean = false



    fun clickCalculate(n: Long) {
        setCalculationBtState(false)
        progressBarState = true
        view.showProgress()
        Thread(Runnable {
            val startTime = System.currentTimeMillis()
            findPrimeNumbers(n)
            val stopTime = System.currentTimeMillis()
            timeElapsed = (stopTime - startTime).toDouble() / 1000
            view.hideProgress()
            progressBarState = false
            view.refreshData(primeNumbers, sumPrimeNumbers, timeElapsed)
            setCalculationBtState(true)
        }).start()
        println(listOf(1, 2, 3) == listOf(1, 2, 3))
    }


    private fun findPrimeNumbers(n: Long) {
        primeNumbers.clear()
        sumPrimeNumbers = 0
        if (n >= 2) {
            primeNumbers.add(2)
            sumPrimeNumbers += 2
        }
        for (i: Long in 3..n step 2) {
            if (isPrime(i)) {
                primeNumbers.add(i)
                sumPrimeNumbers += i
            }
        }
    }


    private fun isPrime(l: Long): Boolean {
        val sqrtL: Long = kotlin.math.sqrt(l.toDouble()).toLong()
        for (i: Long in 3..sqrtL step 2) {
            if (l % i == 0L) return false
        }
        return true
    }


    private fun setCalculationBtState(state: Boolean) {
        calculationBtState = state
        view.calculateBtSetEnabled(calculationBtState)
    }


}