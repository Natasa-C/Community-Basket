package com.example.community_basket.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.community_basket.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginLink.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.registerButton.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val email = binding.registerEmail.text.toString()
        val password = binding.registerPassword.text.toString()

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        } else {
            val sharedPreferences =
                getSharedPreferences("AuthenticationPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.apply {
                putString("EMAIL_KEY", email)
                putString("PASSWORD_KEY", password)
            }.apply()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}