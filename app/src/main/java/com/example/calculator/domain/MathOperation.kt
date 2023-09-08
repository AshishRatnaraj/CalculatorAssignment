package com.example.calculator.domain

sealed class MathOperation(val symbols: String){
    object Addition: MathOperation("+")
    object Subtraction: MathOperation("+")
    object Multiplication: MathOperation("X")
    object Division: MathOperation("/")
}
