package com.example.calculator.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.Key.Companion.Backspace
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.ButtonAction
import com.example.calculator.domain.MathOperation

class CalculatorViewModel: ViewModel() {


 var state by mutableStateOf(CalculatorState())


    private fun clearScreen() {
        TODO("Not yet implemented")
    }

    private fun negate() {
        TODO("Not yet implemented")
    }

    private fun addDecimal() {
        TODO("Not yet implemented")
    }

    private fun calculator() {
        TODO("Not yet implemented")
    }

    private fun writeOperator(operation: MathOperation) {

    }

    private fun writeNumber(number: Int) {

    }

    fun onButton(action: ButtonAction) {
        when (action) {
            is ButtonAction.Number -> writeNumber(action.number)
            is ButtonAction.Operation -> writeOperator(action.operation)
            is ButtonAction.Equals -> calculator()
            is ButtonAction.Decimal -> addDecimal()
            is ButtonAction.Negation -> negate()
            is ButtonAction.Clear -> clearScreen()
            is ButtonAction.Backspace -> backspace()

        }
    }




    private fun backspace() {
        TODO("Not yet implemented")
    }
}
