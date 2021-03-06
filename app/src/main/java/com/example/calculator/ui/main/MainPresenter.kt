package com.example.calculator.ui.main

import com.example.calculator.datainterface.MainView
import com.example.calculator.domain.usecase.MainUseCase

class MainPresenter(private val view: MainView, private val useCase: MainUseCase){

    var historyFlag = false
    fun operationResult(){
        val result = useCase.operationResult()
        if(!result.result){
            view.showError(result.error)
        }else{
            view.displayResult(result.value)
        }
    }

    fun showOperator(s: String){
        val operator = " $s "
        val result = useCase.saveOperator(operator)
        if(result.result){
            view.displayOperations(operator)
        }else{
            view.showError(result.error)
        }
    }

    fun showBrackets(s:String){
        val bracket = if(s == "("){
            "$s "
        }else{
            " $s"
        }

        if(useCase.verifyIsResult()){
            val result = useCase.saveCurrentOperation(bracket)
            if(result.result){
                view.clearDisplay()
            }
        }else{
            useCase.saveCurrentOperation(bracket)
        }
        view.displayOperations(bracket)
    }

    fun showNumber(s: String, textview: String) {
        if(textview == ""){
            val result = useCase.saveCurrentOperation(s)
            if (result.result) {
                view.displayOperations(s)
            }
        }else{
            if(useCase.verifyIsResult()){
                useCase.saveCurrentOperation(s)
                view.updateHistory(textview, s)
            }else{
                val result = useCase.saveCurrentOperation(s)
                if (result.result) {
                    view.displayOperations(s)
                }
            }
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
        useCase.clearDisplay()
        view.clearDisplay()
    }

    fun getItemCount(): Int  = useCase.getItemCount()

    fun getHistoryRow(position: Int) = useCase.getHistoryRow(position)

    fun displayHistory(){
        historyFlag = if(historyFlag){
            view.hideHistory()
            false
        }else{
            useCase.getHistoryLog()
            view.updateData()
            view.displayHistory()
            true
        }
    }
}