package com.example.calculator

class MainUseCase (private val mainRepository: MainRepository){

    fun deleteLast() = mainRepository.deleteLast()
    fun saveCurrentOperation(s:String) = mainRepository.saveCurrentOperation(s)
    //fun clearDisplay() = mainRepository.clearDisplay()
}