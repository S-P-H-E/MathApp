package com.sphe.mathapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var firstNumberText = findViewById<EditText>(R.id.firstNumber)
        var secondNumberText = findViewById<EditText>(R.id.secondNumber)
        var operator = findViewById<TextView>(R.id.operator)
        var result = findViewById<TextView>(R.id.result)

        fun calculateCheck(firstNumber: Int, secondNumber: Int, operator: String): Int {
            var result = 0
            when (operator) {
                "+" -> result = firstNumber + secondNumber
                "-" -> result = firstNumber - secondNumber
                "x" -> result = firstNumber * secondNumber
                "÷" -> result = firstNumber / secondNumber
            }
            return result
        }

        fun calculate(): String {
            var firstNumber = firstNumberText.text.toString()
            var secondNumber = secondNumberText.text.toString()
            var answer = "0"

            if (firstNumber.isNotEmpty() && secondNumber.isNotEmpty()) {
                var firstNumberNull = firstNumber.toIntOrNull()
                var secondNumberNull = secondNumber.toIntOrNull()
                if (firstNumberNull != null && secondNumberNull != null) {
                    answer = calculateCheck(firstNumberNull, secondNumberNull, operator.text.toString()).toString()
                }
            }
            return answer
        }

        operator.setOnClickListener {
            if (operator.text == "+") {
                operator.text = "-"
                result.text = calculate()
            } else if (operator.text == "-") {
                operator.text = "x"
                result.text = calculate()
            } else if (operator.text == "x") {
                operator.text = "÷"
                result.text = calculate()
            } else if (operator.text == "÷") {
                operator.text = "+"
                result.text = calculate()
            }
        }

        firstNumberText.doAfterTextChanged {
            result.text = calculate()
        }

        secondNumberText.doAfterTextChanged {
            result.text = calculate()
        }

    }
}