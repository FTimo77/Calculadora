package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Variables para manejar los cálculos
    private var currentNumber: String = ""  // Número que el usuario está ingresando
    private var firstNumber: Double? = null // Primer número ingresado
    private var operation: String? = null  // Operador seleccionado (+, -, *, /)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a los elementos de la interfaz
        val tvResult: TextView = findViewById(R.id.tvResult)
        val buttons = mapOf(
            R.id.btn0 to "0", R.id.btn1 to "1", R.id.btn2 to "2",
            R.id.btn3 to "3", R.id.btn4 to "4", R.id.btn5 to "5",
            R.id.btn6 to "6", R.id.btn7 to "7", R.id.btn8 to "8",
            R.id.btn9 to "9"
        )

        // Configurar los botones numéricos
        buttons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener {
                appendNumber(value, tvResult)
            }
        }

        // Configurar las operaciones
        findViewById<Button>(R.id.btnAdd).setOnClickListener { selectOperation("+", tvResult) }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { selectOperation("-", tvResult) }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { selectOperation("*", tvResult) }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { selectOperation("/", tvResult) }

        // Configurar botón igual
        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            calculate(tvResult)
        }

        // Configurar botón limpiar
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            clear(tvResult)
        }
    }

    // Método para agregar números al número actual
    private fun appendNumber(number: String, tvResult: TextView) {
        currentNumber += number
        tvResult.text = currentNumber
    }

    // Método para seleccionar una operación
    private fun selectOperation(op: String, tvResult: TextView) {
        if (currentNumber.isEmpty()) {
            Toast.makeText(this, "Ingrese un número primero", Toast.LENGTH_SHORT).show()
            return
        }
        firstNumber = currentNumber.toDouble()
        currentNumber = ""
        operation = op
        tvResult.text = ""
    }

    // Método para realizar el cálculo
    private fun calculate(tvResult: TextView) {
        if (operation == null || currentNumber.isEmpty() || firstNumber == null) {
            Toast.makeText(this, "Operación inválida", Toast.LENGTH_SHORT).show()
            return
        }

        val secondNumber = currentNumber.toDouble()
        val result = when (operation) {
            "+" -> firstNumber!! + secondNumber
            "-" -> firstNumber!! - secondNumber
            "*" -> firstNumber!! * secondNumber
            "/" -> if (secondNumber != 0.0) firstNumber!! / secondNumber else {
                Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show()
                return
            }
            else -> null
        }

        // Mostrar el resultado
        tvResult.text = result.toString()
        currentNumber = result.toString()
        firstNumber = null
        operation = null
    }

    // Método para limpiar los datos
    private fun clear(tvResult: TextView) {
        currentNumber = ""
        firstNumber = null
        operation = null
        tvResult.text = "0"
    }
}
