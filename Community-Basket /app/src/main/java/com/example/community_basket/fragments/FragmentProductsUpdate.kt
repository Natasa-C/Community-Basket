package com.example.community_basket.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
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
        return inflater.inflate(R.layout.fragment_products_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        view.et_update_product_name.setText(args.currentProduct.name)
        view.et_update_product_location.setText(args.currentProduct.location)
        view.et_update_product_price.setText(args.currentProduct.price.toString())
        view.et_update_product_unit.setText(args.currentProduct.unit)

        var spinner : Spinner = view.update_spinner_product_category
        var categoryAdapter : ArrayAdapter<String>? = this.activity?.let {
            ArrayAdapter<String> (
                it,
                android.R.layout.simple_list_item_1,
                resources.getStringArray(R.array.product_categories))
        }

        if (categoryAdapter != null) {
            categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = categoryAdapter
        }

        view.update_product_button.setOnClickListener {
            val category: String = spinner.selectedItem.toString()
            updateItem(category)
        }

        // Add menu
        setHasOptionsMenu(true)
    }

    private fun updateItem(category : String) {
        val name = et_update_product_name.text.toString()
        val location = et_update_product_location.text.toString()
        val price = et_update_product_price.text.toString()
        val unit = et_update_product_unit.text.toString()

        var imageId : String = when (category) {
            "fruits" -> "fruits2"
            "vegetables" -> "vegetables"
            "nuts" -> "nuts"
            "dairy products" -> "dairy2"
            "meats" -> "meat"
            "eggs" -> "eggs"
            "others" -> "iv_unknown"
            else -> "iv_unknown"
        }

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
            mProductViewModel.updateProduct(updatedProduct, imageId)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteProduct()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteProduct() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mProductViewModel.deleteProduct(args.currentProduct)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentProduct.name}",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().navigate(R.id.action_fragmentProductsUpdate2_to_fragmentProductsList)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentProduct.name}?")
        builder.setMessage("Are you sure you want to delete ${args.currentProduct.name}?")
        builder.create().show()
    }
}





