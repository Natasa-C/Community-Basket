package com.example.community_basket.activities

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.community_basket.R
import com.example.community_basket.databinding.ActivityMainBinding
import com.facebook.login.LoginManager
import java.util.jar.Manifest


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var notificationManager: NotificationManagerCompat
    val CHANNEL_ID : String = "MARKET-CHANNEL"


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

                R.id.logout -> {
                    val sharedPreferences =
                        getSharedPreferences("AuthenticationPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()

                    editor.apply {
                        putBoolean("LOGGED_IN", false)
                    }.apply()

                    LoginManager.getInstance().logOut()

                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }

                R.id.market -> {
                    val intent = Intent(this, MarketActivity::class.java)
                    startActivity(intent)
                }
            }

            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            true
        }
        return super.onOptionsItemSelected(item)
    }
}