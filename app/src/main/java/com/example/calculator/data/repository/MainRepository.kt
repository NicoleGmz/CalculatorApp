package com.example.calculator.data.repository

import com.example.calculator.domain.model.OperationResult
import java.lang.Double.parseDouble
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.math.round

class MainRepository {
    private var operation: String = ""

    private var historyOperations: ArrayList<String> = arrayListOf()
    private var historyResults: ArrayList<String> = arrayListOf()

    private var isResult: Boolean = false
    //private var pattern = """((\d+\.?\d*)\s)?(([+*/-]\s)?\(\s(\d+\.?\d*)(\s[+*/-]\s(\d+\.?\d*))+\s\)\s?)?([+*/-]\s(\d+\.?\d*)\s?)?""".toRegex()

    fun deleteLast() {
        operation = operation.dropLast(1)
    }
    
    fun clearDisplay(){
        historyOperations.removeAll(historyOperations)
        historyResults.removeAll(historyResults)
        operation = ""
        isResult = false
    }

    fun saveCurrentOperation(s:String): OperationResult {

        if(isResult){
            operation = ""
        }

        isResult = false

        return if(s == "0" && operation.split(" ").last() == "0"){
            OperationResult(0.0, false, "")
        }else{
            operation = operation.plus(s)
            OperationResult(0.0, true, "")
        }
    }

    fun saveOperator(s: String): OperationResult{
        if(isResult){
            operation = historyResults.last()
        }

        isResult = false

        return if(operation.trim().split(" ").last().contains("""[+\-*/]""".toRegex())){
            OperationResult(0.0,false, "You already have an operator")
        }else{
            operation = operation.plus(s)
            OperationResult(0.0,true, "")
        }

    }

    fun verifyDots(s: String): OperationResult {
        return if (operation.split(" ").last().contains(".")){
            OperationResult(0.0, false, "Already a dot in this number")
        }else{
            operation = operation.plus(s)
            OperationResult(0.0, true, "")
        }
    }

    fun operationResult(): OperationResult {

        operation = operation.trim()
        val listValues = operation.split(" ")

        return if (listValues.size > 1) {
            listValues.forEach { it.trim() }
            val aux = getRPNFormat(listValues)
            historyOperations.add(operation)
            getRPNResult(aux)

        } else {
            if (listValues.size == 1) {
                OperationResult(0.0, false, "An operator and a second number is needed")
            } else {
                OperationResult(0.0, false, "Insert numbers to operate")
            }
        }
    }

    fun verifyIsResult(): Boolean {
        return isResult
    }

    private fun getRPNFormat(values: List<String>): ArrayDeque<String> {
        val operatorStack = ArrayDeque<String>()
        val stackingOperation = ArrayDeque<String>()
        for(token in values){
            when {
                verifyIfNumeric(token) -> {
                    stackingOperation.addLast(token)
                }
                token == "(" -> {
                    operatorStack.addLast(token)
                }
                token == ")" -> {
                    while(operatorStack.isNotEmpty() && operatorStack.last() != "("){
                        stackingOperation.addLast(operatorStack.removeLast())
                    }
                    operatorStack.removeLast()
                }
                else -> {
                    while(operatorStack.isNotEmpty() && (getPreference(token) <= getPreference(operatorStack.last()))){
                        stackingOperation.addLast(operatorStack.removeLast())
                    }
                    operatorStack.addLast(token)
                }
            }
        }
        while(operatorStack.isNotEmpty() ){
            stackingOperation.addLast(operatorStack.removeLast())
        }
        return stackingOperation
    }

    private fun getPreference(s: String): Int{
        return if(s == "*" || s == "/"){
            3
        } else if (s == "+" || s == "-"){
            2
        }else{
            -1
        }
    }

    private fun getRPNResult(values: ArrayDeque<String>): OperationResult{
        val operationPile = ArrayDeque<String>()
        for(value in values){
            if(verifyIfNumeric(value)){
                operationPile.addLast(value)
            }else{
                val result = doOperation(operationPile.removeLast(), operationPile.removeLast(), value)
                operationPile.addLast(result)
            }
        }
        val result = operationPile.last().toDouble().round(3)
        historyResults.add(result.toString())
        isResult = true
        return OperationResult(result, true, "")
    }

    private fun doOperation(firstNumber: String, secondNumber: String, operator: String): String{
        var result = 0.0
        when(operator){
            "+" -> result = addNumbers(firstNumber, secondNumber)
            "-" -> result = subtractNumbers(firstNumber, secondNumber)
            "*" -> result = multiplyNumbers(firstNumber, secondNumber)
            "/" -> result = divideNumbers(secondNumber, firstNumber)
        }
        return result.toString()
    }

    private fun addNumbers(firstOperator: String, secondOperator:String): Double{
        return parseDouble(firstOperator) + parseDouble(secondOperator)
    }

    private fun subtractNumbers(firstOperator: String, secondOperator:String): Double{
        return parseDouble(firstOperator) - parseDouble(secondOperator)
    }

    private fun multiplyNumbers(firstOperator: String, secondOperator:String): Double{
        return parseDouble(firstOperator) * parseDouble(secondOperator)
    }

    private fun divideNumbers(firstOperator: String, secondOperator:String): Double{
        return parseDouble(firstOperator) / parseDouble(secondOperator)
    }

    private fun verifyIfNumeric(value:String): Boolean{
        var numeric = true
        try {
            parseDouble(value)
        } catch (e: NumberFormatException) {
            numeric = false
        }
        return numeric
    }

    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }

}