package com.example.community_basket.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.community_basket.databinding.ActivityLoginBinding
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        checkLoggedIn()

        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            logIn()
        }

        binding.registerLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun logIn() {
        val emailLogin = binding.email.text.toString()
        val passwordLogin = binding.password.text.toString()

        val sharedPreferences = getSharedPreferences("AuthenticationPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val email = sharedPreferences.getString("EMAIL_KEY", null)
        val password = sharedPreferences.getString("PASSWORD_KEY", null)

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && email.equals(emailLogin) && password.equals(passwordLogin)){
            editor.apply {
                putBoolean("LOGGED_IN", true)
            }.apply()

            Toast.makeText(this, "Logged in $email $password", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else{
            Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkLoggedIn() {
        val sharedPreferences = getSharedPreferences("AuthenticationPrefs", Context.MODE_PRIVATE)
        val loggedIn = sharedPreferences.getBoolean("LOGGED_IN", false)

        if (loggedIn){
            Toast.makeText(this, "User is logged", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else{
            Toast.makeText(this, "User is not logged", Toast.LENGTH_LONG).show()
        }
    }
}