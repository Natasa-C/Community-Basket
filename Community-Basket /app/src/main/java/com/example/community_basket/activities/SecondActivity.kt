package com.example.community_basket.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.community_basket.R
import com.example.community_basket.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout,
            R.string.open,
            R.string.close
        )
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

                R.id.market ->{
                    val intent = Intent(this, MarketActivity::class.java)
                    startActivity(intent)
                }
            }

            true
        }

//        setupActionBarWithNavController(findNavController(R.id.list_fragment))

    }
//
//    // back arrow add product
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.list_fragment)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            true
        }
        return super.onOptionsItemSelected(item)
    }
}