package com.example.community_basket.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.community_basket.R
import com.example.community_basket.model.Product

class MarketAdapter: RecyclerView.Adapter<MarketAdapter.ProductViewHolder>() {
    private var productList = emptyList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val productName: TextView = view.findViewById(R.id.product_name)
        private val productLocation: TextView = view.findViewById(R.id.product_location)
        private val productPrice: TextView = view.findViewById(R.id.product_price)
        private val productUnit: TextView = view.findViewById(R.id.product_unit)
        private val movieImage: ImageView = view.findViewById(R.id.iv_product_image)
        private val layout: ConstraintLayout = view.findViewById(R.id.container)

        fun bind(item: Product) {
            var image = R.drawable.iv_unknown
            if (item.imageId.toString() != "0") {
                image = item.imageId!!
            }


            productName.text = item.name
            productLocation.text = item.location
            productPrice.text = item.price.toString()
            productUnit.text = "lei/" + item.unit
            movieImage.setImageDrawable(
                ContextCompat.getDrawable(
                    movieImage.context,
                    image
                )
            )
        }

    }

    fun setData(products: List<Product>) {
        this.productList = products
        notifyDataSetChanged()
    }
}