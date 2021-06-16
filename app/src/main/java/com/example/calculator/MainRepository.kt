package com.example.calculator

class MainRepository {
    var operation: String = ""

    fun saveCurrentOperation(s:String){
        operation.plus(s)
    }

    fun clearDisplay(){
        operation = ""
    }

    fun deleteLast() {
        operation.dropLast(1)
    }

}