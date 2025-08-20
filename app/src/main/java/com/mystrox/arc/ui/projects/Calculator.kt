package com.mystrox.arc.ui.projects

import android.R.attr.maxLines
import android.R.attr.text
import android.R.attr.textStyle
import android.util.Log
import android.util.Log.i
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mystrox.arc.R
import java.util.LinkedList
import java.util.Queue
import java.util.Stack
import kotlin.Char
import kotlin.text.iterator

@Composable
fun Calculator(){
    var fontFam  = FontFamily(
    Font(resId = R.font.seven_segment)
    )
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 50.dp)
    ) {
//        OutlinedTextField(
//            onValueChange = {
//                text = it
//            },
//            value = text,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(100.dp),
//            maxLines = 1,
//            textStyle = TextStyle(
//                fontFamily = fontFam,
//                fontSize = 45.sp,
//                fontWeight = FontWeight.Bold,
//            ),
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Number
//            )
//
//        )
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            maxLines = 1,
            style = TextStyle(
                fontFamily = fontFam,
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),
            contentPadding = PaddingValues(20.dp),
        ) {
            items(12){index ->
                ElevatedButton(
                    onClick = {
                        text += (index+1).toString()
                    },
                    modifier = Modifier
                        .size(250.dp,70.dp)
                ) {
                    Text(
                        text = (index+1).toString(),
                        fontSize = 30.sp,
                        fontFamily = fontFam,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
        Row(
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp)
        ) {
            ElevatedButton(
                onClick = {
                    text += "+"
                },
                modifier = Modifier
                    .size(100.dp, 70.dp)
//                    .padding(start = 80.dp)
            ) {
                Text(
                    text = "+",
                    fontSize = 30.sp,
                    fontFamily = fontFam,
                    fontWeight = FontWeight.Bold
                )
            }
            ElevatedButton(
                onClick = {
                    text +="-"
                },
                modifier = Modifier
                    .size(100.dp, 70.dp),
//                    .padding(start = 80.dp)
            ) {
                Text(
                    text = "-",
                    fontSize = 30.sp,
                    fontFamily = fontFam,
                    fontWeight = FontWeight.Bold
                )
            }
            ElevatedButton(
                onClick = {
                    text = itp(text)
                },
                modifier = Modifier
                    .size(150.dp, 70.dp)
//                    .padding(start = 80.dp)
            ) {
                Text(
                    text = "=",
                    fontSize = 30.sp,
                    fontFamily = fontFam,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

fun Compute(text:String) : String{
    var queue : Queue<Char> = LinkedList()
    var result = 0
    var mediatorL = ""
    var mediatorR = ""
    var opp = ""
//    var liste : ArrayList<String> = arrayListOf()
    for ( ch in text)
    {
        queue.add(ch)
    }
    while(queue.size >1){

        if (queue.peek().isDigit() && queue.size > 1){
            if(opp.isEmpty()){
                mediatorL += queue.poll()
            }
            else{
                mediatorR += queue.poll()
            }
        }
        else{
            if(queue.peek().equals("+")){
                result = mediatorL.toInt() + mediatorR.toInt()
            }

        }
    }


    return result.toString()
}

fun ComputeStack(text:String) : String{
    var stack : Stack<Char> = Stack()
    var stackL : Stack<Char> = Stack()
    var stackR : Stack<Char> = Stack()
    var result = 0
    var mediatorL = ""
    var mediatorR = ""
    var opp = ""
//    var liste : ArrayList<String> = arrayListOf()
    for ( ch in text)
    {
        stack.add(ch)
    }
    while(stack.size >1){

        if (stack.peek().isDigit() && stack.size > 1){
            if(opp.isEmpty()){
                stackL.push(stack.pop())
            }
            else{
                stackR.push(stack.pop())
            }
        }
        else{
            if(opp.isEmpty()){
                opp = stack.pop().toString()
            }
            else {

                while (stackL.size > 0 || stackR.size > 0) {

                    if (stackL.size > 0) {
                        mediatorL += stackL.pop()
                    } else {
                        mediatorR += stackR.pop()
                    }
                }

                if (opp == "+") {
                    result = mediatorL.toInt() + mediatorR.toInt()
                }
                if (opp == "-") {
                    result = mediatorL.toInt() - mediatorR.toInt()
                }
                mediatorR=""
                mediatorL = ""
                opp = ""
            }

        }
    }


    return result.toString()
}

fun itp(text: String) : String{


    if (text.isEmpty()) return ""
    var newText = ""
    var lastopp = true
    for(t in text){
        if(t.isDigit()){
            if(lastopp){
                newText += "<$t"
                lastopp = false
            }else{
                newText += t
                lastopp = false
            }
        }
        if (t == '+' || t == '-'){
            lastopp = true
            newText = newText +">"+ t
        }
    }
    newText += ">"

    Log.i("Postfix nextText", newText)

    val stack = Stack<Char>()
    var postfix=""
    for (ch in newText) {
        if(ch == ')'){
            while(!stack.peek().equals('(')){
                postfix += stack.pop()
            }
            stack.pop()
        }
        if(ch.isDigit() || ch == '<' || ch == '>'){
            postfix+=ch
        }else{
            stack.push(ch)
        }
    }
    while(!stack.isEmpty()){
        postfix += stack.pop()
    }
    Log.i("Postfix", postfix)
    return pe(postfix)
}

fun pe(text: String) : String{
    var stack = Stack<String>()
    for (ch in text){
        if (ch.isDigit() || ch == '<' || ch == '>'){
            stack.push(ch.toString())
        }else{
            if(ch == '+'){
                var r = ""
                var l = ""
                if(stack.peek() == ">" && r.isEmpty()){
                    stack.pop()
                    while (stack.peek() != "<"){
                        r += stack.pop()
                    }
                    stack.pop()
                    r.reversed()
                }
                if(stack.peek() == ">"){
                    stack.pop()
                    while (stack.peek() != "<"){
                        l += stack.pop()
                    }
                    stack.pop()
                    l.reversed()
                }else{
                    r = stack.pop().toString()
                    l = stack.pop().toString()
                }
                Log.i("Left A", l)
                Log.i("Right A", r)
                stack.push((l.toInt() + r.toInt()).toString())
            }
            if(ch == '-'){
                var r = ""
                var l = ""
                if(stack.peek() == ">" && r.isEmpty()){
                    stack.pop()
                    while (stack.peek() != "<"){
                        r += stack.pop()
                    }
                    stack.pop()
                    r.reversed()
                }
                if(stack.peek() == ">"){
                    stack.pop()
                    while (stack.peek() != "<"){
                        l += stack.pop()
                    }
                    stack.pop()
                    l.reversed()
                }else{
                    r = stack.pop().toString()
                    l = stack.pop().toString()
                }
                Log.i("Left S", l)
                Log.i("Right S", r)
                stack.push((l.toInt() - r.toInt()).toString())
            }
        }
    }
    return stack.pop().toString()

}

@Preview
@Composable
fun CalculatorPreview() {
    Calculator()
}
//implementation("net.objecthunter:exp4j:0.4.8")
//
//import net.objecthunter.exp4j.ExpressionBuilder
//
//fun main() {
//    val input = "3 + 2 * 5"
//    val expression = ExpressionBuilder(input).build()
//    val result = expression.evaluate()
//    println(result) // 13.0
//}
