package com.example.community_basket

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.community_basket.SecondActivity
import com.example.community_basket.R
import com.example.community_basket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btActivitySecond.setOnClickListener {
            gotoSecondApp()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container, FirstFragment::class.java, null)
                .commit()
        }
    }

    private fun gotoSecondApp() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}