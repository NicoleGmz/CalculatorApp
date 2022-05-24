package com.example.calculator.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R
import com.example.calculator.data.repository.MainRepository
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.datainterface.MainView
import com.example.calculator.domain.usecase.MainUseCase

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var  button0: Button
    private lateinit var  button1: Button
    private lateinit var  button2: Button
    private lateinit var  button3: Button
    private lateinit var  button4: Button
    private lateinit var  button5: Button
    private lateinit var  button6: Button
    private lateinit var  button7: Button
    private lateinit var  button8: Button
    private lateinit var  button9: Button

    private lateinit var  buttonEqual: Button
    private lateinit var  buttonAdd: Button
    private lateinit var  buttonSub: Button
    private lateinit var  buttonMul: Button
    private lateinit var  buttonDiv: Button
    private lateinit var  buttonDot: Button
    private lateinit var  buttonRBracket: Button
    private lateinit var  buttonLBracket: Button
    private lateinit var  buttonDel: Button
    private lateinit var  buttonC: Button

    private lateinit var buttonHistory: ImageButton
    private lateinit var historyText: TextView

    private lateinit var historyView: TextView
    private lateinit var operationView: TextView
    private lateinit var presenter: MainPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HistoryAdapter

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter = MainPresenter(this, MainUseCase(MainRepository()))

        button0 = binding.Button0
        button1 = binding.Button1
        button2 = binding.Button2
        button3 = binding.Button3
        button4 = binding.Button4
        button5 = binding.Button5
        button6 = binding.Button6
        button7 = binding.Button7
        button8 = binding.Button8
        button9 = binding.Button9

        buttonEqual = binding.ButtonEqual
        buttonAdd = binding.ButtonAdd
        buttonSub = binding.ButtonSub
        buttonMul = binding.ButtonMul
        buttonDiv = binding.ButtonDiv
        buttonDot = binding.ButtonDot
        buttonRBracket = binding.ButtonRightBracket
        buttonLBracket = binding.ButtonLeftBracket
        buttonDel = binding.ButtonDel
        buttonC = binding.ButtonC

        buttonHistory = binding.historyImageButton
        historyText = binding.HistoryButton
        historyView = binding.HistoryTextView
        operationView = binding.OperationTextView
        operationView.hint = 0.toString()

        adapter = HistoryAdapter(presenter)
        recyclerView = binding.historyView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.alpha = 0F

        setListeners()
    }

    private fun setListeners(){

        button0.setOnClickListener {
            presenter.showNumber(button0.text.toString(), operationView.text.toString())
        }

        button1.setOnClickListener {
            presenter.showNumber(button1.text.toString(), operationView.text.toString())
        }

        button2.setOnClickListener {
            presenter.showNumber(button2.text.toString(), operationView.text.toString())
        }

        button3.setOnClickListener {
            presenter.showNumber(button3.text.toString(), operationView.text.toString())
        }

        button4.setOnClickListener {
            presenter.showNumber(button4.text.toString(), operationView.text.toString())
        }

        button5.setOnClickListener {
            presenter.showNumber(button5.text.toString(), operationView.text.toString())
        }

        button6.setOnClickListener {
            presenter.showNumber(button6.text.toString(), operationView.text.toString())
        }

        button7.setOnClickListener {
            presenter.showNumber(button7.text.toString(), operationView.text.toString())
        }

        button8.setOnClickListener {
            presenter.showNumber(button8.text.toString(), operationView.text.toString())
        }

        button9.setOnClickListener {
            presenter.showNumber(button9.text.toString(), operationView.text.toString())
        }

        buttonAdd.setOnClickListener {
            presenter.showOperator(buttonAdd.text.toString())
        }

        buttonSub.setOnClickListener {
            presenter.showOperator(buttonSub.text.toString())
        }

        buttonMul.setOnClickListener {
            presenter.showOperator(buttonMul.text.toString())
        }

        buttonDiv.setOnClickListener {
            presenter.showOperator(buttonDiv.text.toString())
        }

        buttonRBracket.setOnClickListener {
            presenter.showBrackets(buttonRBracket.text.toString())
        }

        buttonLBracket.setOnClickListener {
            presenter.showBrackets(buttonLBracket.text.toString())
        }

        buttonDot.setOnClickListener {
            presenter.showDot(buttonDot.text.toString())
        }

        buttonEqual.setOnClickListener {
            presenter.operationResult()
        }

        buttonDel.setOnClickListener {
            presenter.deleteLast(operationView.text.length)
        }

        buttonC.setOnClickListener {
            presenter.globalClear()
        }

        buttonHistory.setOnClickListener {
            presenter.displayHistory()
        }

        historyText.setOnClickListener {
            presenter.displayHistory()
        }

    }

    override fun clearDisplay() {
        historyView.text = ""
        operationView.text = ""
        operationView.hint = 0.toString()
    }

    override fun deleteLast() {
        operationView.text = operationView.text.dropLast(2)

    }

    override fun displayOperations(operation: String) {
        operationView.text = operationView.text.toString().plus(operation)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun updateHistory(historyResult: String, newOperation: String) {
        historyView.text = historyView.text.toString().plus("\n $historyResult")
        operationView.text = newOperation
    }

    override fun displayResult(result: Double) {
        historyView.text = operationView.text
        operationView.text = result.toString()
    }

    override fun displayHistory() {
        recyclerView.alpha = 1F
        buttonHistory.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
    }

    override fun hideHistory(){
        recyclerView.alpha = 0F
        buttonHistory.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
    }

    override fun updateData(){
        adapter.updateData()
    }

}