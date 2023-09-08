package com.example.calculator.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.domain.MathOperation
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.screenBackground
import java.lang.reflect.Modifier

@Composable
fun DisplayScreen (state: CalculatorState, modifier: Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip()
            .background(screenBackground)
            .padding(6.dp)
    ){



    }
}

@Composable
private fun ScreenRepo(fontSize:Int, text: String,modifier: Modifier){
    Row(modifier = modifier){
        Spacer(modifier = Modifier.width(4.dp))
        Text(
           text = text,
           fontSize = fontSize.sp,
           maxLines = 1,
            overflow =TextOverflow.Ellipsis
        )
       Spacer(modifier =Modifier.width(4.dp))

    }
}


@Composable
fun DisplayScreenPreview(){
    CalculatorTheme() {
        var state = CalculatorState()
        state = state.copy(
            displayNumber = "3.14",
            number1 = "3",
            number2 = "3.14",
            operation = MathOperation.Multiplication,
            currentEquation = "3 x 3.14"
        )
        DisplayScreen(state = state)
    }
    }



