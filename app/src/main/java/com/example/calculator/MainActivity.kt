package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false
    var out: Boolean = false
    var interim: Double = 0.0
    var iscleared: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var layout =  findViewById<LinearLayout>(R.id.main_layout)
    }

    fun onDigit(view: View){
        if ((userinput.text).length == 12){
            Toast.makeText(this, "Reached Max Limit", Toast.LENGTH_SHORT).show()
        }
        else if (iscleared){
            userinput.text=""
            userinput.append((view as Button).text)
            iscleared=false
        }
        else {
            userinput.append((view as Button).text)
        }
        lastNumeric = true
        if(out) {
            calculate(view)
        }
    }

    fun onClear(view: View){
        userinput.text=""
        output.text=""
        out=false
        lastNumeric = false
        lastDot = false
        interim = 0.0
    }

    fun onDecimal(view: View){
        if (lastNumeric && !lastDot) {
            userinput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun calculate(view: View){

        if((true)){
            var input = userinput.text.toString()
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
                    // output.text = removeExtraZero((one.toDouble() - two.toDouble()).toString())
                    interim = one.toDouble() - two.toDouble()
                }
                else if(input.contains("+")) {
                    val splitValue = input.split("+")
                    // 100-31
                    var one = splitValue[0] // 100
                    var two = splitValue[1] // 31
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    // output.text = removeExtraZero((one.toDouble() + two.toDouble()).toString())
                    interim = one.toDouble() + two.toDouble()
                }
                else if(input.contains("*")) {
                    val splitValue = input.split("*")
                    // 100-31
                    var one = splitValue[0] // 100
                    var two = splitValue[1] // 31
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    // output.text = removeExtraZero((one.toDouble() * two.toDouble()).toString())
                    interim = one.toDouble() * two.toDouble()
                }
                else if(input.contains("/")) {
                    val splitValue = input.split("/")
                    // 100-31
                    var one = splitValue[0] // 100
                    var two = splitValue[1] // 31
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    // output.text = removeExtraZero((one.toDouble() / two.toDouble()).toString())
                    interim = one.toDouble() / two.toDouble()
                }else {out = false}
                if (out) {output.text = removeExtraZero(interim.toString())}
            }catch (e: ArithmeticException){e.printStackTrace()}
        }
    }

    fun onEqual(view: View){
        /*
        if(lastNumeric){
            var input = userinput.text.toString()
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
        */
        userinput.text=output.text
        output.text=""
        iscleared = true
        lastNumeric = false
        lastDot = false
        out = false
    }

    private fun removeExtraZero(result: String): String{
        var value = result
        if (result.takeLast(2) == ".0"){
            value = result.substring(0, result.length - 2)
        }
        else if (result.contains(".")){
            val splitvalue = value.split(".")
            val val1 = splitvalue[0]
            val val2 = splitvalue[1]
            var req = 1
            if (val2.length == 1 || val2.length == 2) {req = val2.length}
            else if (val2.contains('E')) {
                req = val2.length
                Toast.makeText(this, "You reached lower limit", Toast.LENGTH_SHORT).show()
            }
            else {req = 3}
            value = val1 + "." + val2.substring(0,req)
        }
        return value
    }

    fun onOperator(view: View){
        if((lastNumeric) && (!isOpeatorAdded(userinput.text.toString()))){
            userinput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
            out = true
        }
        else if (isOpeatorAdded(userinput.text.toString())){
            userinput.text = removeExtraZero(interim.toString())
            userinput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
            out = true
            output.text= removeExtraZero(interim.toString())
        }
        else if ((view  as Button).text == "-"){
            userinput.append((view as Button).text)
        }
    }

    private fun isOpeatorAdded(value: String) : Boolean{
        return if (value.startsWith("-") && !value.substring(1).contains("-")) {false}
        else (value.contains("/") || value.contains("*")
                || value.contains("+") || (value.contains("-")))
    }
}
