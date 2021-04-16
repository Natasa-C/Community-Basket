package com.example.community_basket.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.community_basket.R
import com.example.community_basket.databinding.ActivityMainBinding
import com.facebook.FacebookSdk
import com.facebook.FacebookSdk.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.fragment_first.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

                R.id.products -> {
                    val intent = Intent(this, SecondActivity::class.java)
                    startActivity(intent)
                }

                R.id.login -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }

            true
        }

        binding.btLogout.setOnClickListener() {
            val sharedPreferences = getSharedPreferences("AuthenticationPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.apply {
                putBoolean("LOGGED_IN", false)
            }.apply()

            LoginManager.getInstance().logOut()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

//        binding.btActivitySecond.setOnClickListener {
//            gotoSecondApp()
//        }
//
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .setReorderingAllowed(true)
//                .add(R.id.fragment_container, FirstFragment::class.java, null)
//                .commit()
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            true
        }
        return super.onOptionsItemSelected(item)
    }
}