package com.example.calculator

interface MainView {

    fun displayOperations(s: String)
    fun deleteLast()
    fun clearDisplay()
    fun showError(s: String)
}