package com.example.calculator

class MainUseCase (private val mainRepository: MainRepository){

    fun deleteLast() = mainRepository.deleteLast()
    fun saveCurrentOperation(s:String) = mainRepository.saveCurrentOperation(s)
    fun operationResult() = mainRepository.operationResult()
    //fun clearDisplay() = mainRepository.clearDisplay()
}