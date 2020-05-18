package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = true
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        output.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View){
        output.text=""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimal(view: View){
        if (lastNumeric && !lastDot) {
            output.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var input = output.text.toString()
            var prefix = ""
            try {
                if(input.startsWith("-")){
                    prefix="-"
                    input = input.substring(1)
                }

                if(input.contains("-")) {
                    val splitValue = input.split("-")
                    // 100-31
                    var one = splitValue[0] // 100
                    var two = splitValue[1] // 31
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    output.text = removeExtraZero((one.toDouble() - two.toDouble()).toString())
                }
                else if(input.contains("+")) {
                    val splitValue = input.split("+")
                    // 100-31
                    var one = splitValue[0] // 100
                    var two = splitValue[1] // 31
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    output.text = removeExtraZero((one.toDouble() + two.toDouble()).toString())
                }
                else if(input.contains("*")) {
                    val splitValue = input.split("*")
                    // 100-31
                    var one = splitValue[0] // 100
                    var two = splitValue[1] // 31
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    output.text = removeExtraZero((one.toDouble() * two.toDouble()).toString())
                }
                else if(input.contains("/")) {
                    val splitValue = input.split("/")
                    // 100-31
                    var one = splitValue[0] // 100
                    var two = splitValue[1] // 31
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    output.text = removeExtraZero((one.toDouble() / two.toDouble()).toString())
                }
            }catch (e: ArithmeticException){e.printStackTrace()}
        }
    }

    private fun removeExtraZero(result: String): String{
        var value = result
        if (result.contains(".")){
            val splitvalue = value.split(".")
            val val1 = splitvalue[0]
            val val2 = splitvalue[1]
            value = val1 + "." + val2.substring(0,1)
        }
        else value = result
        return value
    }

    fun onOperator(view: View){
        if(lastNumeric && !isOpeatorAdded(output.text.toString())){
            output.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun isOpeatorAdded(value: String) : Boolean{
        return if (value.startsWith("-")) {false}
        else {value.contains("/") || value.contains("*")
                || value.contains("+") || (value.contains("-"))}
    }
}
