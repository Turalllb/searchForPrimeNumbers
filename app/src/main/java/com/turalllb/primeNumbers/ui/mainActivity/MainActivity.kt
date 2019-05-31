package com.turalllb.primeNumbers.ui.mainActivity

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.turalllb.primeNumbers.R
import com.turalllb.primeNumbers.ui.MyApplication.Companion.INSTANCE
import com.turalllb.primeNumbers.ui.mainActivity.adapter.AdapterPrimeNumbers


class MainActivity : AppCompatActivity(), MainView {
    lateinit var presenter: Presenter<MainView>
    private lateinit var adapterPrimeNumbers: AdapterPrimeNumbers
    private lateinit var editText: EditText
    private lateinit var calculateBt: Button
    private lateinit var sumTv: TextView
    private lateinit var calculationTimeTv: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = INSTANCE

        //region findViewById
        calculateBt = findViewById(R.id.calculate_bt)
        val textLayout = findViewById<TextInputLayout>(R.id.text_input_layout)
        editText = textLayout.findViewById(R.id.editText)
        sumTv = findViewById(R.id.sum_tv)
        calculationTimeTv = findViewById(R.id.calculation_time_tv)
        val rv: RecyclerView = findViewById(R.id.rv)
        progressBar = findViewById(R.id.progressBar)
        //endregion

        progressBar.visibility = if(presenter.progressBarState) View.VISIBLE else View.INVISIBLE

        calculateBt.isEnabled = presenter.calculationBtState
        sumTv.text = getString(R.string.sum, presenter.sumPrimeNumbers)
        calculationTimeTv.text = getString(R.string.calculation_time, presenter.timeElapsed)

        rv.layoutManager = LinearLayoutManager(this)
        adapterPrimeNumbers = AdapterPrimeNumbers(this, presenter.primeNumbers)
        rv.adapter = adapterPrimeNumbers

    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
    }


    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }

    fun onClickCalculate(v: View?) {
        hideKeyboard()
        val input = editText.text.toString()
        if (!input.equals("")) {
            presenter.clickCalculate(input.toLong())
        }

    }


    override fun showProgress() {
        runOnUiThread {
            Runnable {
                progressBar.visibility = View.VISIBLE
            }.run()
        }
    }

    override fun hideProgress() {
        runOnUiThread {
            Runnable {
                progressBar.visibility = View.INVISIBLE
            }.run()
        }
    }

    override fun refreshData(primeNumbers: List<Long>, sumPrimeNumbers: Long, timeElapsed: Double) {
        runOnUiThread {
            Runnable {
                setSum(sumPrimeNumbers)
                setCalculationTime(timeElapsed)
                adapterPrimeNumbers.notifyDataSetChanged()
                calculateBt.isEnabled = true
            }.run()
        }
    }


    private fun setSum(sumPrimeNumbers: Long) {
        sumTv.text = getString(R.string.sum, sumPrimeNumbers)
    }

    private fun setCalculationTime(timeElapsed: Double) {
        calculationTimeTv.text = getString(R.string.calculation_time, timeElapsed)
    }

    override fun calculateBtSetEnabled(state: Boolean) {
        runOnUiThread {
            Runnable {
                calculateBt.isEnabled = state
            }.run()
        }
    }

    private fun hideKeyboard(){
        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = getCurrentFocus()
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }
}

