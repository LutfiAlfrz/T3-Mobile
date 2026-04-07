package com.example.liniermarch31

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import androidx.core.text.set
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var rbMale: RadioButton
    private lateinit var rbFemale: RadioButton
    private lateinit var cbReading: CheckBox
    private lateinit var cbCoding: CheckBox
    private lateinit var cbSports: CheckBox
    private lateinit var btnSubmit: Button
    private lateinit var tvHasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById<EditText>(R.id.etName)
        rgGender = findViewById<RadioGroup>(R.id.rgGender)
        rbMale = findViewById<RadioButton>(R.id.rbMale)
        rbFemale = findViewById<RadioButton>(R.id.rbFemale)
        cbReading = findViewById<CheckBox>(R.id.cbReading)
        cbCoding = findViewById<CheckBox>(R.id.cbCoding)
        cbSports = findViewById<CheckBox>(R.id.cbSports)
        btnSubmit = findViewById<Button>(R.id.btnSubmit)
        tvHasil = findViewById<TextView>(R.id.tvHasil)

        btnSubmit.setOnClickListener {
            showData()
        }
    }

    private fun showData(){
        val name = etName.text.toString().trim()
        var isValid = true

        if (name.isEmpty()) {
            etName.error = "Nama tidak boleh kosong!"
            etName.requestFocus()
            isValid = false
        } else {
            etName.error = null
        }

        if(rgGender.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        val gender = when (rgGender.checkedRadioButtonId) {
            R.id.rbMale -> "Laki-laki"
            R.id.rbFemale -> "Perempuan"
            else -> "-"
        }

        val hobbyList = mutableListOf<String>()
        if (cbReading.isChecked) hobbyList.add("Membaca")
        if (cbCoding.isChecked) hobbyList.add("Coding")
        if (cbSports.isChecked) hobbyList.add("Olahraga")

        val hobby = if (hobbyList.isEmpty()) "Tidak ada" else hobbyList.joinToString(", ")

        val teks = "Nama    : $name\nKelamin    : $gender\nHobi     : $hobby"
        val spannable = SpannableString(teks)

        spannable.setSpan(StyleSpan(Typeface.BOLD), 0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        val genderStart = teks.indexOf("Kelamin")
        spannable.setSpan(StyleSpan(Typeface.BOLD), genderStart, genderStart + 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        val hobbyStart = teks.indexOf("Hobi")
        spannable.setSpan(StyleSpan(Typeface.BOLD), hobbyStart, hobbyStart + 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        tvHasil.text = spannable
        tvHasil.setTextColor(Color.parseColor("#1565C0"))
    }
}