package com.example.community_basket

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.community_basket.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()

        binding.btSave.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val insertedText = binding.tvEdit.toString()
        binding.tvFromPreferences.text = insertedText

        val sharedPreferences = getSharedPreferences("firstSharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.apply {
            putString("STRING_KEY", insertedText)
            putBoolean("BOOLEAN_KEY", binding.switch1.isChecked)
        }.apply()

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("firstSharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY", null)
        val savedBoolean = sharedPreferences.getBoolean("BOOLEAN_KEY", false)

        binding.tvFromPreferences.text = savedString
        binding.switch1.isChecked = savedBoolean
    }

}