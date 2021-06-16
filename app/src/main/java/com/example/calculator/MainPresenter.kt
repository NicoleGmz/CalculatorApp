package com.example.calculator

class MainPresenter(private val view: MainView){

    fun showNumber(s: String){
        view.displayNumber(s)
    }
}