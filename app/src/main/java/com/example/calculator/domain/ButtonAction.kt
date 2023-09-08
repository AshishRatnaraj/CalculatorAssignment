package com.example.calculator.domain

import android.widget.Button

sealed class ButtonAction {
    object Clear: ButtonAction()
    object Backspace: ButtonAction()
    object Equals: ButtonAction()
    object Negation:ButtonAction()
   data class Operation(val operation: MathOperation): ButtonAction()
   data class Number(val number:Int): ButtonAction()
    object Decimal: ButtonAction()
}