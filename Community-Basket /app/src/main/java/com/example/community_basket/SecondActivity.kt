package com.example.community_basket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.community_basket.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBarWithNavController(findNavController(R.id.list_fragment))

    }

    // back arrow add product
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.list_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}