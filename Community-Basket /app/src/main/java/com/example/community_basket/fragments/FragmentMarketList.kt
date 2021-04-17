package com.example.community_basket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.community_basket.R
import com.example.community_basket.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_market_list.view.*

class FragmentMarketList : Fragment() {
    private val adapter = MarketAdapter()
    private val products = ArrayList<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_market_list, container, false)

        // Recyclerview
        view.recycler_view_market.adapter = adapter

        readDataFirestore()

        return view
    }

    private fun readDataFirestore() {
        val db = FirebaseFirestore.getInstance()
        db.collection("products")
            .get()
            .addOnCompleteListener {

                if (it.isSuccessful) {
                    it.result?.forEach { document ->

                        val name = document.data.getValue("name").toString()
                        val location = document.data.getValue("location").toString()
                        val price = document.data.getValue("price").toString()
                        val unit = document.data.getValue("unit").toString()
                        val imageId = document.data.getValue("imageId").toString()
                        products.add(
                            Product(
                                0,
                                name,
                                location,
                                price.toFloat(),
                                unit,
                                resources.getIdentifier(
                                    imageId,
                                    "drawable",
                                    context?.packageName
                                )
                            )
                        )
                    }

                    adapter.setData(products)
                }
            }
    }
}