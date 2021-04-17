package com.example.community_basket.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.community_basket.R
import com.example.community_basket.model.Product
import com.example.community_basket.viewmodel.ProductViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_products_add.*
import kotlinx.android.synthetic.main.fragment_products_add.view.*
import kotlinx.android.synthetic.main.fragment_second.*
import java.lang.StringBuilder


class FragmentProductsAdd : Fragment() {

    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_products_add, container, false)

        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        view.add_product_button.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val name = et_product_name.text.toString()
        val location = et_product_location.text.toString()
        val price = et_product_price.text.toString()
        val unit = et_product_unit.text.toString()
        val imageId = et_product_image.text.toString()

        if (inputCheck(name, location, price, unit, imageId)) {
            // create Product Object
            val product = Product(
                0,
                name,
                location,
                price.toFloat(),
                unit,
                resources.getIdentifier(imageId, "drawable", context?.packageName)
            )

            // add data to the local database
            mProductViewModel.addProduct(product)

            // add data to Firebase
            addToFirebase(name, location, price.toFloat(), unit, imageId)

            findNavController().navigate(R.id.action_fragmentProductsAdd_to_fragmentProductsList)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
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

    private fun addToFirebase(
        name: String,
        location: String,
        price: Float,
        unit: String,
        imageId: String
    ) {
        val db = FirebaseFirestore.getInstance()
        val product: MutableMap<String, Any?> = HashMap()

        product["name"] = name
        product["location"] = location
        product["price"] = price
        product["unit"] = unit
        product["imageId"] = imageId

        db.collection("products")
            .add(product)
            .addOnSuccessListener {
                Toast.makeText(context, "Product added successfully", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed to add", Toast.LENGTH_LONG).show()
            }
    }
}