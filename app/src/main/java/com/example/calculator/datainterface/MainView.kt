package com.example.calculator.datainterface

interface MainView {

    fun displayOperations(operation: String)
    fun deleteLast()
    fun clearDisplay()
    fun showError(error: String)
    fun displayResult(result: Double)
    fun updateHistory(historyResult: String, newOperation: String)
    fun displayHistory()
    fun hideHistory()
    fun updateData()
}