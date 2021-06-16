package com.example.calculator

import java.lang.Double.parseDouble
import kotlin.math.roundToInt

class MainRepository {
    private var operation: String = ""

    fun deleteLast() {
        operation = operation.dropLast(1)
    }
    
    fun clearDisplay(){
        operation = ""
    }

    fun saveCurrentOperation(s:String):OperationResult{
        return if(s == "0" && operation.split(" ").last() == "0"){
            OperationResult(0, false, "")
        }else{
            operation = operation.plus(s)
            OperationResult(0, true, "")
        }
    }

    fun verifyDots(s: String): OperationResult {
        return if (operation.split(" ").last().contains(".")){
            OperationResult(0, false, "Already a dot in this number")
        }else{
            operation = operation.plus(s)
            OperationResult(0, true, "")
        }
    }

    fun operationResult(): OperationResult{

        lateinit var result: OperationResult

        val listValues = operation.split(" ")

        result = if(listValues.size > 1){
            listValues.forEach { it.trim() }
            val lastValue = listValues.last()
            if(!verifyIfNumeric(lastValue)){
                OperationResult(0,false, "Need a number at the end")
            }else{
                doOperation(listValues)
            }
        }else{
            if (listValues.size == 1){
                OperationResult(0,false, "An operator and a second number is needed")
            }else{
                OperationResult(0,false, "Insert numbers to operate")
            }
        }
        return result
    }

    private fun doOperation(values:List<String>): OperationResult{

        val iter = 0
        val firstNumber = values[iter]
        val operator = values[iter+1]
        val secondNumber = values[iter+2]
        var result = 0
        when(operator){
            "+" -> result = addNumbers(firstNumber, secondNumber).roundToInt()
            "-" -> result = subtractNumbers(firstNumber, secondNumber).roundToInt()
            "*" -> result = multiplyNumbers(firstNumber, secondNumber).roundToInt()
            "/" -> result = divideNumbers(firstNumber, secondNumber).roundToInt()
        }
        return OperationResult(result, true, "")
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

}