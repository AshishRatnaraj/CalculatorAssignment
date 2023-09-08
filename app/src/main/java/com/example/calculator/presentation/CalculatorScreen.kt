package com.example.calculator.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.domain.ButtonAction
import com.example.calculator.presentation.CalculatorState
import com.example.calculator.domain.MathOperation
import com.example.calculator.ui.theme.CalculatorTheme
import java.lang.reflect.Modifier


@Composable
fun CalculatorState(
    state: CalculatorState,
    onButtonPress: (ButtonAction) -> Unit
 ){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(horizontal = 6.dp)
            .padding(horizontal = 8.dp)
        verticalArrangment = Arrangement.spaced(4.dp)
        horizontalAlignment = Alignment.End
    ){
        HistoryDisplay(state = state, modifier = Modifier.weight(if) )
        DisplayScreen(state = state)
        Keypad(onButtonPress = onButtonPress)


        )
    }
}
@Preview
@Composable
fun CalculatorStatePreview(){
    CalculatorTheme(){
      var state = CalculatorState()
      state = state.copy(
          var state = CalculatorState()
        state = state.copy(
            displayNumber = "3.14",
            number1 = "3",
            number2 = "3.14",
            operation = MathOperation.Multiplication,
            currentEquation = "3 x 3.14",
            equations = listOf("7 x /7 =49", "16/2 = 8", "1 + 2 = 3")
        )
      CalculatorScreen(state = state, onButtonPress  = { } )
        )
    }

}