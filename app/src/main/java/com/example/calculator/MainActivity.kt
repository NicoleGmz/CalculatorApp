package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    lateinit var  button0: Button
    lateinit var  button1: Button
    lateinit var  button2: Button
    lateinit var  button3: Button
    lateinit var  button4: Button
    lateinit var  button5: Button
    lateinit var  button6: Button
    lateinit var  button7: Button
    lateinit var  button8: Button
    lateinit var  button9: Button

    lateinit var  buttonEqual: Button
    lateinit var  buttonPlus: Button
    lateinit var  buttonSub: Button
    lateinit var  buttonMul: Button
    lateinit var  buttonDiv: Button
    lateinit var  buttonDot: Button
    lateinit var  buttonRBracket: Button
    lateinit var  buttonLBracket: Button
    lateinit var  buttonDel: Button
    lateinit var  buttonC: Button

    lateinit var historyView: TextView
    lateinit var operationView: TextView
    lateinit var presenter: MainPresenter

    lateinit var binding: ActivityMainBinding
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
        buttonPlus = binding.ButtonPlus
        buttonSub = binding.ButtonSub
        buttonMul = binding.ButtonMul
        buttonDiv = binding.ButtonDiv
        buttonDot = binding.ButtonDot
        buttonRBracket = binding.ButtonRightBracket
        buttonLBracket = binding.ButtonLeftBracket
        buttonDel = binding.ButtonDel
        buttonC = binding.ButtonC

        historyView = binding.HistoryTextView
        operationView = binding.OperationTextView
        operationView.hint = 0.toString()

        setListeners()
    }

    private fun setListeners(){

        button0.setOnClickListener {
            presenter.showNumber(button0.text.toString())
        }

        button1.setOnClickListener {
            presenter.showNumber(button1.text.toString())
        }

        button2.setOnClickListener {
            presenter.showNumber(button2.text.toString())
        }

        button3.setOnClickListener {
            presenter.showNumber(button3.text.toString())
        }

        button4.setOnClickListener {
            presenter.showNumber(button4.text.toString())
        }

        button5.setOnClickListener {
            presenter.showNumber(button5.text.toString())
        }

        button6.setOnClickListener {
            presenter.showNumber(button6.text.toString())
        }

        button7.setOnClickListener {
            presenter.showNumber(button7.text.toString())
        }

        button8.setOnClickListener {
            presenter.showNumber(button8.text.toString())
        }

        button9.setOnClickListener {
            presenter.showNumber(button9.text.toString())
        }

        buttonPlus.setOnClickListener {
            presenter.showOperator(buttonPlus.text.toString())
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

    }

    override fun clearDisplay() {
        historyView.text = ""
        operationView.text = ""
        operationView.hint = 0.toString()
    }

    override fun deleteLast() {
        operationView.text = operationView.text.dropLast(1)

    }

    override fun displayOperations(s: String) {
        operationView.text = operationView.text.toString().plus(s)
    }

    override fun showError(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}