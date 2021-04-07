package com.example.community_basket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.community_basket.ProductModel
import com.example.community_basket.R
import com.example.community_basket.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_products_list.view.*

class FragmentProductsList : Fragment() {
    private val adapter = ProductAdapter()
    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_products_list, container, false)

        // Recyclerview
        view.recycler_view.adapter = adapter

        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        mProductViewModel.readAllData.observe(viewLifecycleOwner, Observer {products ->
            adapter.setData(products)
        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentProductsList_to_fragmentProductsAdd2)
        }

        return view
    }

}