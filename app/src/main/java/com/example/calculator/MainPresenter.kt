package com.example.calculator

class MainPresenter(private val view: MainView, private val useCase: MainUseCase){

    fun showNumber(s: String){
        useCase.saveCurrentOperation(s)
        view.displayOperations(s)
    }

    fun deleteLast(number:Int){
        if(number == 0){
            view.clearDisplay()
        }else{
            useCase.deleteLast()
            view.deleteLast()
        }
    }

    fun globalClear(){
        view.clearDisplay()
    }
}