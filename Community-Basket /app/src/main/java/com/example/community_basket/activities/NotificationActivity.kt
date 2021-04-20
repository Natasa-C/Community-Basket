package com.example.community_basket.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.community_basket.databinding.ActivityNotificationBinding
import com.example.community_basket.model.Product


class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("product")) {
            var product : Product? = intent.getParcelableExtra("product")
            if (product != null) {
                val successfullyInsertionMessage : String = "Produsul " + product.name + " a fost adaugat in Market la pretul de " + product.price.toString() + " lei pe " + product.unit
                binding.textView.setText(successfullyInsertionMessage)

                binding.btOpenMarket.setOnClickListener {
                    val intent : Intent = Intent (this, MarketActivity::class.java)
                    startActivity(intent)
                }
            }
            else {
                Log.d("product", "Extra is null!")
            }
        }
        else {
            Log.d("product", "Intent does not have extra!")
        }
    }
}