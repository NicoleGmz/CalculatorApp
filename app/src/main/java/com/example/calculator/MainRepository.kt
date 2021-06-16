package com.example.calculator

import java.lang.Double.parseDouble

class MainRepository {
    private var operation: String = ""

    fun saveCurrentOperation(s:String){
        operation = operation.plus(s)
    }

    fun deleteLast() {
        operation.dropLast(1)
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

    fun verifyIfNumeric(value:String): Boolean{
        var numeric = true
        try {
            val num = parseDouble(value)
        } catch (e: NumberFormatException) {
            numeric = false
        }
        return numeric
    }

    /*fun clearDisplay(){
    operation = ""
}*/
}