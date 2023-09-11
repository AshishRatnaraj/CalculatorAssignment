package com.example.calculator.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.ButtonAction
import com.example.calculator.domain.MathOperation

class CalculatorViewModel: ViewModel() {


    var state by mutableStateOf(CalculatorState())


    private fun writeOperator(operation: MathOperation) {
        val isFirstOperator = state.operation == null
        val shouldOverwriteOperator = state.operation != null && state.number2 == ""
        val shouldUseLastResult = state.number1 == "" && state.displayNumber != ""
        val alreadyHasOperationCalculatorState = state.number1 != "" && state.number2 != ""

        if (shouldUseLastResult) {

            state = state.copy(
                operation = operation,
                number2 = state.displayNumber
            )
        } else if (alreadyHasOperationCalculatorState) {
            calculate()
            state = state.copy(
                operation = operation,
                number1 = state.displayNumber
            )
        } else if (isFirstOperator || shouldOverwriteOperator)
            state = state.copy(
                operation = operation
            )
        state = state.copy(
            currentEquation = writePartialEquation()
        )
    }


    fun onButton(action: ButtonAction) {
        when (action) {
            is ButtonAction.Number -> writeNumber(action.number)
            is ButtonAction.Operation -> writeOperator(action.operation)
            is ButtonAction.Equals -> calculate()
            is ButtonAction.Decimal -> addDecimal()
            is ButtonAction.Negation -> negate()
            is ButtonAction.Clear -> clearScreen()
            is ButtonAction.Backspace -> backspace()

        }
    }

    fun clearScreen() {
        state = CalculatorState(
            equations = state.equations

        )
    }

    fun negate() {
        val resultFromLastOperation = state.displayNumber != "" && state.number1 == ""
        val zeroShouldNotBeNegative =
            state.displayNumber == "" || state.displayNumber.toDoubleOrNull() == 0.0
        val negateFirstNumber = state.number2 == " " && state.number1 != ""

        if (resultFromLastOperation) {
            val negatedvalue = negatevalue(state.displayNumber)
            state = state.copy(
                displayNumber = negatedvalue
            )
        } else if (zeroShouldNotBeNegative) {
            return
        } else if (negateFirstNumber) {
            val negatedValue = negatevalue(state.number1)
            state = state.copy(
                number1 = negatedValue,
                displayNumber = negatedValue
            )
        } else {
            val negatedvalue = negatevalue (state.number2)
            state = state.copy(
                number2 = negatedvalue,
                displayNumber = negatedvalue
            )
        }
        state = state.copy(currentEquation = writePartialEquation())

    }

    private fun negatevalue(number: String): String {
        val alreadyNegative = number.contains("-")
        val negatedvalue = if (alreadyNegative) number.drop(1) else "-" + number
        return negatedvalue

    }

    fun calculate() {
        val operand1 = state.number1.toDoubleOrNull()
        val operand2 = state.number2.toDoubleOrNull()

        if (operand1 != null && operand2 != null) {
            val result: Double
            val equation: String
            when (state.operation) {
                is MathOperation.Addition -> {
                    result = operand1 + operand2
                    equation = writeFullEquation(result)
                }
                is MathOperation.Subtraction -> {
                    result = operand1 - operand2
                    equation = writeFullEquation(result)
                }
                is MathOperation.Multiplication -> {
                    result = operand1 * operand2
                    equation = writeFullEquation(result)
                }
                is MathOperation.Division -> {
                    result = operand1 / operand2
                    equation = writeFullEquation(result)
                }
                else -> return
            }
            state = state.copy(
                displayNumber = dropZerosAfterDecimal(result),
                currentEquation = "",
                operation = null,
                number1 = "",
                number2 = "",
                equations = state.equations + equation
            )
        }
    }


    fun addDecimal() {
        val isFirstNumber = state.operation == null
        if (isFirstNumber) {
            val number1 = addDecimal(state.number1)
            state = state.copy(
                number1 = number1,
                displayNumber = number1,
            )

        } else {
            val number2 = addDecimal(state.number2)
            state = state.copy(
            displayNumber = number2,
                number2 = number2
            )
        }

        state = state.copy(currentEquation = writePartialEquation())
    }
    private fun addDecimal(number: String): String{
        return if (number == "") {
            "0"
        } else if (number.contains(".")){
           number
        } else{
            "$number."
        }
    }

    private fun writeNumber(newNumber: Int) {
        val displayNumber: String
        val isFirstNumber = state.operation == null

        if (isFirstNumber) {
            displayNumber = state.number1 + newNumber.toString()
            state = state.copy(
                displayNumber = displayNumber,
                number1 = displayNumber,
                currentEquation = displayNumber
            )
            return
        } else {
            displayNumber = state.number2 + newNumber.toString()
            state = state.copy(
                displayNumber = displayNumber,
                number2 = displayNumber,
                currentEquation = state.currentEquation + newNumber.toString()
            )
        }

    }


    private fun backspace() {
        val onNumber2 = state.number2.isNotBlank()
        val onOperator = state.operation != null
        val onNumber1 = state. number1.isNotBlank()

        when {
            onNumber2 -> state = state.copy(
                number2 = state.number2.dropLast(1),
                displayNumber = state.displayNumber.dropLast(1),
                currentEquation = state.currentEquation.dropLast(1),

                )
            onNumber2 -> state = state.copy(
                operation = null,
                currentEquation = state.number1


            )
            onNumber1 -> state = state.copy(
                number1 = state.number1.dropLast(1),
                displayNumber = state.number1.dropLast(1),
                currentEquation = state.number1.dropLast(1)
            )
        }
            if(state.displayNumber == "" || state.displayNumber == "-"){
                if(state.number1 != "" && state.number1 != "-") {
                    state = state.copy(
                        displayNumber = state.number1,
                        number2 = ""
                    )
                }else{
                    clearScreen()
                }
            }
                    state = state.copy(
                    currentEquation = writePartialEquation()
                    )
        }






    private fun writePartialEquation(): String{
        val equation = if(state.operation !=null){
            state.number1 + " " + state.operation!!.symbol + " " +state.number2
        }else{
             state.number1
        }
      return equation
    }


    private fun writeFullEquation(result: Double): String {
        val resultString = dropZerosAfterDecimal(result)
        val equation = if (state.operation != null) {
            state.number1 + " " + state.operation!!.symbol + " " +state.number2 +" = " + resultString
        }else{
         state.number1
        }
        return equation
    }
    private fun dropZerosAfterDecimal(number: Double):String{
        return if (isWholeNumber(number)) {
            number.format(0)
        } else {
            return number.toString()
        }
    }

    private fun Double.format(scale: Int) = "%.${scale}f".format(this)


  private fun isWholeNumber(number: Double):Boolean{
      if (number % 1 == 0.0) {
          return true
      }
     return false

  }

}
