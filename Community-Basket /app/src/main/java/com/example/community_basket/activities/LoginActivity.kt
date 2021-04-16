package com.example.community_basket.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.community_basket.databinding.ActivityLoginBinding
import com.facebook.*
import com.facebook.AccessToken
import com.facebook.GraphRequest.GraphJSONObjectCallback
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var callbackManager: CallbackManager

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

        callbackManager = CallbackManager.Factory.create();

        binding.facebookLoginButton.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    Log.d("Facebook login", "SUCCESS")
                }

                override fun onCancel() {
                    Log.d("Facebook login", "CANCEL")
                }

                override fun onError(exception: FacebookException) {
                    Log.d("Facebook login", "ERROR")
                }
            })
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        var graphRequest: GraphRequest = GraphRequest.newMeRequest(
            AccessToken.getCurrentAccessToken(),
            GraphJSONObjectCallback { user, response ->
//                Log.d("Facebook login", user.toString())

                val sharedPreferences = getSharedPreferences("AuthenticationPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                editor.apply {
                    putBoolean("LOGGED_IN", true)
                }.apply()

                goToMainActivity()
            })

        var bundle: Bundle = Bundle()
        bundle.putString("fields", "name, id")
        graphRequest.parameters = bundle
        graphRequest.executeAsync()
    }

//    var accesTokenTracker: AccessTokenTracker = object : AccessTokenTracker() {
//        override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken, currentAccessToken: AccessToken) {
//            if (currentAccessToken == null) {
//                LoginManager.getInstance().logOut()
//            }
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        accesTokenTracker.startTracking()
//    }

    private fun logIn() {
        val emailLogin = binding.email.text.toString()
        val passwordLogin = binding.password.text.toString()

        val sharedPreferences = getSharedPreferences("AuthenticationPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val email = sharedPreferences.getString("EMAIL_KEY", null)
        val password = sharedPreferences.getString("PASSWORD_KEY", null)

        if (email.equals(emailLogin) && password.equals(passwordLogin)){
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