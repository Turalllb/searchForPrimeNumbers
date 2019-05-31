package com.turalllb.primeNumbers.ui.mainActivity

import org.junit.Test

import org.junit.Assert.*
import java.util.*

class PresenterTest {
    private val primeNumbers: MutableList<Long> = arrayListOf()
    private var sumPrimeNumbers: Long = 0
    private val realPrimeNumbers: List<Long> = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)



    @Test
    fun clickCalculate() {
        val n = 100L
        findPrimeNumbers(n)
        assertTrue(primeNumbers == realPrimeNumbers)
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
}