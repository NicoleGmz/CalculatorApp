package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

    lateinit var presenter: MainPresenter

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter = MainPresenter(this)

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
    }

    fun setListeners(){
        button0.setOnClickListener {
            presenter.showNumber(button0.text.toString())
        }
    }

    override fun displayNumber(s: String) {
        TODO("Not yet implemented")
    }
}