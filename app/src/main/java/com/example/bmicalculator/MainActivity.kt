package com.example.bmicalculator

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculator.databinding.ActivityMainBinding
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var weight = 0
    var height = 0


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.weightPicker.minValue = 30
        binding.weightPicker.maxValue = 150

        binding.heightPicker.minValue = 50
        binding.heightPicker.maxValue = 200

        val button = findViewById<Button>(R.id.submit)

        binding.weightPicker.setOnValueChangedListener { _,_,_ ->

            weight = binding.weightPicker.value

        }

        binding.heightPicker.setOnValueChangedListener { _,_,_ ->

            height = binding.heightPicker.value
        }

        button.setOnClickListener {
            calculateBMI()

        }


    }
    private fun calculateBMI() {

        val doubleheight = height.toDouble() / 100
        val bmi = weight.toDouble() / (doubleheight * doubleheight)

        binding.resultsTV.text = String.format("Your BMI is : %.2f", bmi)

        binding.messageTV.text = String.format("%s", healthymessage(bmi))
        colorSelection(bmi)

    }
    private fun colorSelection(bmi : Double) {
        val exampleTxt = findViewById<TextView>(R.id.messageTV)
        if(bmi < 18.5)
            exampleTxt.setTextColor(Color.parseColor("#9C27B0"))
        if ((bmi < 25.0) && (bmi > 18.5))
            exampleTxt.setTextColor(Color.parseColor("#0F9D58"))
        if (bmi < 30.0 && bmi > 25.0)
            exampleTxt.setTextColor(Color.parseColor("#FF0000"))
        if(bmi > 30.0)
            exampleTxt.setTextColor(Color.parseColor("#FFFF00FF"))
    }

    private fun healthymessage(bmi: Double): String {
        if (bmi < 18.5)
            return "Underwight"
        if (bmi < 25.0)
            return "Healthy"
        if (bmi < 30.0)
            return "Overweight"

        return "Obesity"
    }
}
