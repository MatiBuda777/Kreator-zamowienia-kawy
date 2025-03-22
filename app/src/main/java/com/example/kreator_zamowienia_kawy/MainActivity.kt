package com.example.kreator_zamowienia_kawy

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val radioGroupCoffee = findViewById<RadioGroup>(R.id.radioCoffee)

        val checkboxMilk = findViewById<CheckBox>(R.id.checkboxMilk)
        val checkboxSugar = findViewById<CheckBox>(R.id.checkboxSugar)

        val seekBarCoffeeNumber = findViewById<SeekBar>(R.id.seekbarCoffeeNumber)
        val seekBarCoffeeNumber2 = findViewById<SeekBar>(R.id.seekbarCoffeeNumber)

        val ImageViewCoffee = findViewById<ImageView>(R.id.imageViewCoffee)
        val textCoffeeType = findViewById<TextView>(R.id.textCoffeeType)
        val textMilkAndSugar = findViewById<TextView>(R.id.textMilkAndSugar)
        val textCoffeeNumber2 = findViewById<TextView>(R.id.textCoffeeNumber2)


        radioGroupCoffee.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)

            when (checkedId) {
                R.id.coffeeEspresso -> {
                    ImageViewCoffee.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.espresso))
                }
                R.id.coffeeCappuccino -> {
                    ImageViewCoffee.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.capuccino))
                }
                R.id.coffeeLatte -> {
                    ImageViewCoffee.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.latte))
                }
            }
            textCoffeeType.text = "Kawa ${selectedRadioButton.text}"
        }

        var dodatki: String = ""
        checkboxMilk.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (!dodatki.contains("Mleko")) dodatki += if (dodatki.isNotEmpty()) " i Mleko" else "Mleko"
            } else {
                dodatki = dodatki.replace(" i Mleko", "").replace("Mleko", "").trim()
            }
            if (dodatki == "i") dodatki = ""
            else if (dodatki[0] == 'i') dodatki.removePrefix("i ").trim()
            textMilkAndSugar.text = dodatki
        }

        checkboxSugar.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (!dodatki.contains("Cukier")) dodatki += if (dodatki.isNotEmpty()) " i Cukier" else "Cukier"
            } else {
                dodatki = dodatki.replace(" i Cukier", "").replace("Cukier", "").trim()
            }
            if (dodatki == "i") dodatki = ""
            else if (dodatki[0] == 'i') dodatki.removePrefix("i ").trim()
            textMilkAndSugar.text = dodatki
        }


        val textCoffeeNumber = findViewById<TextView>(R.id.textCoffeeNumber)
        seekBarCoffeeNumber.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textCoffeeNumber.text = "Ilość kaw: ${progress}"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        val button = findViewById<Button>(R.id.buttonSubmit)
        button.setOnClickListener {
            seekBarCoffeeNumber2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textCoffeeNumber2.text = "Ilość kaw: ${progress}"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

            Toast.makeText(this, "Złożono zamówienie!", Toast.LENGTH_SHORT).show()
        }
    }
}