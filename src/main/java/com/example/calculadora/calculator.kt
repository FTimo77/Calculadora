package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class calculator (val num1 : Int, val num2 : Int, val process :  Int){

    fun operar(): Int {
        return when (process) {
            1 -> num1 + num2  // Si process es 1, retorna la suma
            2 -> num1 - num2  // Si process es 2, retorna la resta
            3 -> num1 * num2  // Si process es 3, retorna la multiplicación
            4 -> num1 / num2  // Si process es 4, retorna la división
            else -> 0
        }
    }
}