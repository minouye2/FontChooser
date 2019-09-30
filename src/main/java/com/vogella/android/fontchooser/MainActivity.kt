package com.vogella.android.fontchooser

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.Typeface.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.textView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent: Intent = getIntent()
        val bundleInfo: Bundle? = intent.getExtras()
        if (bundleInfo != null) {

        }
        setResult(Activity.RESULT_OK, intent)
        finish()

        val spinner = findViewById<Spinner>(R.id.fontDropdown) as Spinner

        val fonts = arrayOf("DEFAULT", "DEFAULT BOLD", "MONOSPACE", "SANS SERIF", "SERIF")

        val adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item, fonts)

        var tf : Typeface = DEFAULT

        var bi = 0

        var ts = 14F

        val rg = findViewById<RadioGroup>(R.id.colorGroup)
        rg?.setOnCheckedChangeListener { _, checked ->
            if (R.id.redButton == checked) {
                textView.setTextColor(Color.rgb(255, 0, 0))

            }
            if (R.id.greenButton == checked) {
                textView.setTextColor(Color.rgb(0, 255, 0))

            }
            if (R.id.blueButton == checked) {
                textView.setTextColor(Color.rgb(0, 0, 255))
            }
            if (R.id.blackButton == checked) {
                textView.setTextColor(Color.rgb(0, 0, 0))
            }
        }

        fun resetToggle() {
            if(boldToggle.isChecked) {
                boldToggle.toggle()
                bi = 0
            }
            if(italicToggle.isChecked) {
                italicToggle.toggle()
                bi = 0
            }
        }

        spinner.adapter = adapter

        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                if(p2 == 0) {
                    resetToggle()
                    tf = DEFAULT
                }
                if(p2 == 1) {
                    resetToggle()
                    boldToggle.toggle()
                    bi = 1
                    tf = DEFAULT_BOLD
                }
                if(p2 == 2) {
                    resetToggle()
                    tf = MONOSPACE
                }
                if(p2 == 3) {
                    resetToggle()
                    tf = SANS_SERIF
                }
                if(p2 == 4) {
                    resetToggle()
                    tf = SERIF
                }
                textView.setTypeface(tf, bi)
            }
        }

        boldToggle.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                bi = 1
                if(italicToggle.isChecked) {
                    bi = 3
                }
            }
            else {

                bi = 0
                if(italicToggle.isChecked) {
                    bi = 2
                }
            }
            textView.setTypeface(tf, bi)
        }
        italicToggle.setOnCheckedChangeListener { _, isChecked ->

            if(isChecked) {
                bi = 2
                if(boldToggle.isChecked) {
                    bi = 3
                }
            }
            else {
                bi = 0
                if(boldToggle.isChecked) {
                    bi = 1
                }
            }
            textView.setTypeface(tf, bi)
        }

        sizeIncrease.setOnClickListener {
            ts += 5F
            textView.textSize = ts
        }
        sizeDecrease.setOnClickListener {
            ts -= 5F
            textView.textSize = ts
        }

    }



}
