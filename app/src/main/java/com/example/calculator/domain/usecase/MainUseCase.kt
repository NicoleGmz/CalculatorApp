package com.example.calculator.domain.usecase

import com.example.calculator.data.repository.MainRepository

class MainUseCase (private val mainRepository: MainRepository){

    fun deleteLast() = mainRepository.deleteLast()
    fun saveCurrentOperation(s: String) = mainRepository.saveCurrentOperation(s)
    fun operationResult() = mainRepository.operationResult()
    fun verifyDots(s: String) = mainRepository.verifyDots(s)
    fun clearDisplay() = mainRepository.clearDisplay()
    fun verifyIsResult() = mainRepository.verifyIsResult()
}