package com.example.community_basket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.community_basket.R
import com.example.community_basket.model.Product
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

        products.add(
            Product(
                0,
                "name",
                "location",
                2.toFloat(),
                "unit",
                resources.getIdentifier("iv_product_image", "drawable", context?.packageName)
            )
        )
        adapter.setData(products)

        return view
    }
}