package com.example.community_basket.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.community_basket.R
import com.example.community_basket.databinding.ActivityMainBinding
import com.example.community_basket.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var message: String? = getIntent().getStringExtra("message")

        binding.textView.setText(message)
    }
}