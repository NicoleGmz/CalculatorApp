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

    fun showNumber(s: String) {
        val result = useCase.saveCurrentOperation(s)
        if (result.result) {
            view.displayOperations(s)
        }
    }

    fun showDot(s: String){
        val result =  useCase.verifyDots(s)
        if(result.result){
            view.displayOperations(s)
        }else{
            view.showError(result.error)
        }
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