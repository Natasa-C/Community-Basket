package com.example.community_basket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    private val localDataSet: List<ProductModel>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CustomAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(localDataSet[position], position)
    }

    override fun getItemCount(): Int {
        return localDataSet.size
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val productName: TextView = view.findViewById(R.id.product_name)
        private val productLocation: TextView = view.findViewById(R.id.product_location)
        private val productPrice: TextView = view.findViewById(R.id.product_price)
        private val productUnit: TextView = view.findViewById(R.id.product_unit)
        private val movieImage: ImageView = view.findViewById(R.id.iv_product_image)
        private val layout: ConstraintLayout = view.findViewById(R.id.container)

//        onclicklistener here

        fun bind(item: ProductModel, position: Int) {
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

            layout.setOnClickListener {
                itemClickListener.onItemClick(item)
            }
        }

    }
}