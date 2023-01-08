package eu.tutorials.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var result: TextView?=null
    private var lastnum: Boolean=false
    private var lastdot: Boolean=false
    private var lastope: Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        result=findViewById(R.id.result)

    }

    fun onDigit(view: View)
    {
        result?.append((view as Button).text)
        lastnum=true
        lastdot=false

    }
    fun clear(view:View)
    {
        result?.text=""
    }
    fun decimalpoint(view: View)
    {
        if( lastnum && !lastdot)
        {
            result?.append(".")
            lastnum=false
            lastdot=true
        }
    }

    fun onOperrator(view:View) {
        result?.text?.let {

        if (lastnum && !isOperatorAdded(it.toString()))
        {
            result?.append((view as Button).text)
            lastnum=false
            lastdot=false
        }
    }

    }
    fun onEqual(view:View)
    {
        if(lastnum)
        {
            var tvValue=result?.text.toString()
            var prefix=""
            try{
                if(tvValue.startsWith("-"))
                {
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    result?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }
                else if(tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    result?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }
                else if(tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    result?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }
                else if(tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    result?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }
                else if(tvValue.contains("%")) {
                    val splitValue = tvValue.split("%")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    result?.text = removeZeroAfterDot((one.toDouble() % two.toDouble()).toString())
                }
                else if(tvValue.contains("^")) {
                    val splitValue = tvValue.split("^")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    result?.text = removeZeroAfterDot((Math.pow(one.toDouble() , two.toDouble())).toString())
                }
            }
            catch (e: ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }
    private fun isOperatorAdded(value:String):Boolean
    {
        return if(value.startsWith("-"))
        {
            false
        }
        else
        {
            value.contains("/")||value.contains("*")|| value.contains("-")||value.contains("-")
        }

    }
    private fun removeZeroAfterDot(res:String):String
    {
        var value=res
        if(res.contains(".0"))
        {
            value=res.substring(0,res.length-2)
        }
        return value
    }

}