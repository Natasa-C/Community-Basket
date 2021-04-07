package com.example.community_basket.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.community_basket.ProductModel
import com.example.community_basket.R

class ProductAdapter() : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private var productList = emptyList<ProductModel>()

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

        fun bind(item: ProductModel) {
            productName.text = item.product_name
            productLocation.text = item.product_location
            productPrice.text = item.product_price.toString()
            productUnit.text = "lei/" + item.product_unit
            movieImage.setImageDrawable(
                ContextCompat.getDrawable(
                    movieImage.getContext(),
                    item.imageId
                )
            )

//            to see how items are recycled
//            if(position == 0){
//                layout.setBackgroundColor(Color.YELLOW)
//            }

//            layout.setOnClickListener {
//                itemClickListener.onItemClick(item)
//            }
        }

    }

    fun setData(products: List<ProductModel>){
        this.productList = products
        notifyDataSetChanged()
    }
}