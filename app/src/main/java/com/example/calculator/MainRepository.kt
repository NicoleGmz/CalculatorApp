package com.example.calculator

import java.lang.Double.parseDouble

class MainRepository {
    private var operation: String = ""

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

    fun deleteLast() {
        operation = operation.dropLast(1)
    }

    fun operationResult(): OperationResult{

        lateinit var result: OperationResult

        val listValues = operation.split(" ")

        if(listValues.size > 1){
            listValues.forEach { it.trim() }
            val lastValue = listValues.last()
            if(!verifyIfNumeric(lastValue)){
                result = OperationResult(0,false, "Need a number at the end")
            }
        }else{
            result = if (listValues.size == 1){
                OperationResult(0,false, "An operator and a second number is needed")
            }else{
                OperationResult(0,false, "Insert numbers to operate")
            }
        }
        return result
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

    /*fun clearDisplay(){
    operation = ""
}*/
}