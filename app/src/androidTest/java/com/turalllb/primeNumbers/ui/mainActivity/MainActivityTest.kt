package com.turalllb.primeNumbers.ui.mainActivity

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import com.turalllb.primeNumbers.R
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @Rule
    public var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    private val inputNumber: String = ""

    @Before
    fun setUp() {
    }

    @Test
    fun inputNumberTest(){
        Espresso.onView(withId(R.id.editText)).perform(typeText(inputNumber))
        Espresso.onView(withId(R.id.calculate_bt)).perform(click())
    }



    @After
    fun tearDown() {
    }
}