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
import androidx.navigation.fragment.navArgs
import com.example.community_basket.R
import com.example.community_basket.model.Product
import com.example.community_basket.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_products_add.*
import kotlinx.android.synthetic.main.fragment_products_add.view.*
import kotlinx.android.synthetic.main.fragment_products_update.*
import kotlinx.android.synthetic.main.fragment_products_update.view.*

class FragmentProductsUpdate : Fragment() {

    private val args by navArgs<FragmentProductsUpdateArgs>()
    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_products_update, container, false)
        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        view.et_update_product_name.setText(args.currentProduct.name)
        view.et_update_product_location.setText(args.currentProduct.location)
        view.et_update_product_price.setText(args.currentProduct.price.toString())
        view.et_update_product_unit.setText(args.currentProduct.unit)

//        can be replaced after removing images without background img id
        var imgName = args.currentProduct.imageId.toString()

        if (args.currentProduct.imageId != 0)
            imgName = resources.getResourceEntryName(args.currentProduct.imageId!!);
        view.et_update_product_image.setText(imgName)

        view.update_product_button.setOnClickListener {
            updateItem()
        }

        return view
    }

    private fun updateItem() {
        val name = et_update_product_name.text.toString()
        val location = et_update_product_location.text.toString()
        val price = et_update_product_price.text.toString()
        val unit = et_update_product_unit.text.toString()
        val imageId = et_update_product_image.text.toString()

        if (inputCheck(name, location, price, unit, imageId)) {
            // create Product Object
            val updatedProduct = Product(
                args.currentProduct.id,
                name,
                location,
                price.toFloat(),
                unit,
                resources.getIdentifier(imageId, "drawable", context?.packageName)
            )

            // update current product in the database
            mProductViewModel.updateProduct(updatedProduct)

            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_fragmentProductsUpdate2_to_fragmentProductsList)
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

}