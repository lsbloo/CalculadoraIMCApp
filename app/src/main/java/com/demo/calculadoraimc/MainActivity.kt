package com.demo.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private val edtAge: EditText by lazy {
        findViewById(R.id.edt_age)
    }
    private val edtWeight: EditText by lazy {
        findViewById(R.id.edt_weight)
    }
    private val edtHeight: EditText by lazy {
        findViewById(R.id.edt_height)
    }
    private val btnCalc: Button by lazy {
        findViewById(R.id.btn_calc)
    }
    private val txtResult: TextView by lazy {
        findViewById(R.id.txt_result)
    }
    private val txtResultTablex: TextView by lazy {
        findViewById(R.id.txt_result_table)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCalc.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_calc -> {
                calcImc()
            }
        }
    }

    private fun calcImc() {
        val height = edtHeight.text.toString()
        val age = edtAge.text.toString()
        val weight = edtWeight.text.toString()
        var txtResultTable: String

        getValues(height, age, weight, fun(heightDouble, _, weightDouble) {
            val imc: Double = weightDouble / (heightDouble * heightDouble)
            txtResultTable = when {
                imc < 18.5 -> {
                    "Abaixo do Peso"
                }
                imc in 18.5..24.9 -> {
                    "Normal"
                }
                imc in 30.0..34.9 -> {
                    "Obesidade Grau 1"
                }
                imc in 35.0..39.9 -> {
                    "Obesidade Grau 2"
                }
                else -> {
                    "Obsedidade Morbida"
                }
            }
            txtResult.text = "O seu IMC é de: ${imc.toString().take(4)}"
            txtResultTablex.text = txtResultTable
        })
    }

    private fun getValues(
        height: String,
        age: String,
        weight: String,
        setupInput: (Double, Int, Double) -> Unit
    ) {
        setupInput(
            height.replace(",", ".").toDouble(),
            age.toInt(),
            weight.replace(",", ".").toDouble()
        )
    }
}