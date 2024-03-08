package com.calculator.binary


import com.calculator.binary.ui.theme.BinaryTheme


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BinaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    Greeting("Android")
                    DefaultApp()
                }

            }
        }
    }
}


@Composable
fun DefaultApp(){
    var flag1 by remember { mutableStateOf(false) }
    var flag2 by remember { mutableStateOf(false) }
    var flag3 by remember { mutableStateOf(false) }
    var flag4 by remember {
        mutableStateOf(false)
    }
    var number1 by remember {
        mutableStateOf("")
    }
    var number2 by remember {
        mutableStateOf("")
    }
    var result by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Calculator App")

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { flag4=!flag4}) {
            Text(text="Switch Between Decimal And Integer")
        }
        OutlinedTextField(
            value = number1,
            onValueChange = { number1 = it },
            label = { Text("Enter Binary Number 1") }
        )
        Spacer(modifier = Modifier.width(10.dp))
        OutlinedTextField(
            value = number2,
            onValueChange = { number2 = it },
            label = { Text("Enter Binary Number 2") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {flag1=true}) {
            Text(text = "Submit")
        }
        if(flag1){
            Button(onClick = {flag2=!flag2}) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Perform Addition")

            }
            Button(onClick = {flag3=!flag3}) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Perform Subtraction")
            }
        }
        if(flag4) {
            if (flag2) {
                val num1 = Integer.parseInt(number1, 2)
                val num2 = Integer.parseInt(number2, 2)
                val sum = num1 + num2
                result = Integer.toBinaryString(sum)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Result: $result")
            }
            if (flag3) {
                val num1 = Integer.parseInt(number1, 2)
                val num2 = Integer.parseInt(number2, 2)
                val sum = num1 - num2
                result = Integer.toBinaryString(sum)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Result: $result")
            }
        }
        else{
            if (flag2) {
                val num1 = binaryToDecimal(number1)
                val num2 = binaryToDecimal(number2)
                val sum = num1 + num2
                result = decimalToBinary(sum)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Result: $result")
            }
            if (flag3) {
                val num1 = binaryToDecimal(number1)
                val num2 = binaryToDecimal(number2)
                val sum = num1 - num2
                result = decimalToBinary(sum)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Result: $result")
            }
        }

    }

}
fun binaryToDecimal(binary: String): Double {
    var binary = binary
    var decimal = 0.0
    var n = 0
    var temp = 0.0
    var remainder: Int
    val negative = binary[0] == '-'
    if (negative) {
        binary = binary.substring(1)
    }
    val fractionIndex = binary.indexOf('.')
    if (fractionIndex != -1) {
        for (i in fractionIndex + 1 until binary.length) {
            temp += ((binary[i] - '0') / 2.0.pow(n.toDouble()))
            n++
        }
        binary = binary.substring(0, fractionIndex)
    }
    n = 0
    while (binary.isNotEmpty()) {
        remainder = binary[binary.length - 1] - '0'
        binary = binary.substring(0, binary.length - 1)
        decimal += (remainder * 2.0.pow(n.toDouble()))
        n++
    }
    decimal += temp
    return if (negative) -decimal else decimal
}

fun decimalToBinary(decimal: Double): String {
    val wholePart = decimal.toLong()
    var fractionalPart = decimal - wholePart
    var binary = ""
    binary += if (wholePart == 0L) "0" else java.lang.Long.toBinaryString(wholePart)
    if (fractionalPart > 0) {
        binary += "."
        while (fractionalPart > 0) {
            fractionalPart *= 2
            val bit = fractionalPart.toInt()
            if (bit == 1) {
                fractionalPart -= bit.toDouble()
                binary += "1"
            } else {
                binary += "0"
            }
        }
    }
    return binary
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BinaryTheme {
        DefaultApp()
    }
}



