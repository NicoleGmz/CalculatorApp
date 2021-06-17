package com.example.calculator.datainterface

interface MainView {

    fun displayOperations(s: String)
    fun deleteLast()
    fun clearDisplay()
    fun showError(s: String)
    fun displayResult(i: Int)

}