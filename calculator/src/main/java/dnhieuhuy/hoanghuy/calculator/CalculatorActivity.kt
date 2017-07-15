package dnhieuhuy.hoanghuy.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_calculator.*
import kotlinx.android.synthetic.main.activity_calculator.view.*
import java.io.IOException


class CalculatorActivity : AppCompatActivity() {

    private var userIsInTheMiddleOfTyping = false

    val operations = mapOf<String, (Double, Double) -> Double>(
            "+" to {a, b -> a + b},
            "–" to {a, b -> a - b},
            "÷" to {a, b -> a / b},
            "×" to {a, b -> a * b}
    )

    data class PendingOperation(val firstNumber: Double, val operation: (Double, Double) -> Double)
    private var pendingOperation: PendingOperation? = null

    fun performPendingOperation(secondNumber: Double): Double
    {
        return pendingOperation?.operation!!.invoke(pendingOperation!!.firstNumber, secondNumber)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

    }

    /**
     * Updates the display when the number
     * key is pressed
     * */
    fun numberPressed(vied: View)
    {
        val button  = vied as Button
        val digit = button.text
        try {
            if(userIsInTheMiddleOfTyping)
            {
                display.append(digit)
            }else
            {
                display.text = button.text
                userIsInTheMiddleOfTyping = true
            }
        }catch (ex: Exception)
        {
            ex.printStackTrace()
        }


    }

    /**
     * Creates Peding Operations and Executes then when
     * a operation key is pressed
     * */
    fun performOperation(view: View)
    {
        val button = view as Button
        var temp: Double
        temp = display.text.toString().toDouble()
        display.text = ""

        try {
            if(display.text != "")
            {
                if(pendingOperation == null) //---> If no operation is pending
                {
                    if(button.text != "=")
                    {
                        pendingOperation = PendingOperation(temp, operations[button.text]!!)
                    }
                }
                else
                {
                    // If the currently pressed key is not "=", then create another pending operation
                    if(button.text != "=")
                    {
                        pendingOperation = PendingOperation(temp, operations[button.text]!!)
                        display.text = ""
                    }
                    display.text = performPendingOperation(temp).toString() //--->Performs the pending operation
                    pendingOperation = null
                }
            }
        }catch (ex: Exception)
        {
            ex.printStackTrace()
        }

    }

    /**
     * Clears the display
     * */
    fun clearDisplay(view: View) {
        if (userIsInTheMiddleOfTyping) {
            display.text = ""
        }
    }
}

