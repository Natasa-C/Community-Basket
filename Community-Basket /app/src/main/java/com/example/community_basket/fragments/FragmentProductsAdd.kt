package com.example.community_basket.fragments

import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.community_basket.R
import com.example.community_basket.activities.NotificationActivity
import com.example.community_basket.model.Product
import com.example.community_basket.viewmodel.ProductViewModel
import com.facebook.FacebookSdk.getApplicationContext
import kotlinx.android.synthetic.main.fragment_products_add.*
import kotlinx.android.synthetic.main.fragment_products_add.view.*


class FragmentProductsAdd : Fragment() {

    private lateinit var mProductViewModel: ProductViewModel
    private lateinit var currentProduct : Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        view.add_product_button.setOnClickListener {
            insertDataToDatabase()
        }

        attachObservers()
    }

    private fun insertDataToDatabase() {
        val name = et_product_name.text.toString()
        val location = et_product_location.text.toString()
        val price = et_product_price.text.toString()
        val unit = et_product_unit.text.toString()
        val imageId = et_product_image.text.toString()

        if (inputCheck(name, location, price, unit, imageId)) {
            currentProduct = Product(
                0,
                name,
                location,
                price.toFloat(),
                unit,
                resources.getIdentifier(imageId, "drawable", context?.packageName)
            )
            mProductViewModel.addProduct(currentProduct, imageId)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun attachObservers() {
        mProductViewModel.responseInsert.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Success to add", Toast.LENGTH_LONG).show()
                sendNotification()
                findNavController().navigate(R.id.action_fragmentProductsAdd_to_fragmentProductsList)
            }
            else
                Toast.makeText(context, "Failed to add", Toast.LENGTH_LONG).show()
        }
    }

    private fun sendNotification() {
        val CHANNEL_ID : String = "MARKET-CHANNEL"
        val notificationManager = NotificationManagerCompat.from(getApplicationContext())

        val intent = Intent (getActivity(), NotificationActivity::class.java)
        intent.putExtra("product", currentProduct)
        val contentIntent = PendingIntent.getActivity(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        var builder= NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_message)
            .setContentTitle("Community Basket notification")
            .setContentText("Produsul a fost adaugat in Market!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setLights(Color.GREEN, 3000, 3000)
            .setContentIntent(contentIntent)

        notificationManager.notify(1, builder.build())
    }

    private fun inputCheck(
        name: String,
        location: String,
        price: String,
        unit: String,
        imageId: String
    ): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(location) || TextUtils.isEmpty(unit) || TextUtils.isEmpty(
            price
        ) || TextUtils.isEmpty(imageId))
    }
}