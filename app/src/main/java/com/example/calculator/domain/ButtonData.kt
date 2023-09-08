package com.example.calculator.domain

data class ButtonData(
    val text: String,
    val onPress: () -> Unit

)
