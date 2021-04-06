package com.example.community_basket.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.community_basket.R
import kotlinx.android.synthetic.main.fragment_products_list.view.*

class FragmentProductsList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_products_list, container, false)

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentProductsList_to_fragmentProductsAdd2)
        }

        return view
    }
}