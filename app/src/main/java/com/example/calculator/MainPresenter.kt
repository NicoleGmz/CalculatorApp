package com.example.calculator

class MainPresenter(private val view: MainView, private val useCase: MainUseCase){

    fun operationResult(){
        val result = useCase.operationResult()
        if(!result.result){
            view.showError(result.error)
        }
    }

    fun showOperator(s: String){
        val operator = " $s "
        useCase.saveCurrentOperation(operator)
        view.displayOperations(operator)
    }

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