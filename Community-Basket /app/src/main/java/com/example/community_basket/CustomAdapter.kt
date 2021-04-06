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
        private val title: TextView = view.findViewById(R.id.product_name)
        private val duration: TextView = view.findViewById(R.id.product_location)
        private val movieImage: ImageView = view.findViewById(R.id.iv_product_image)
        private val layout: ConstraintLayout = view.findViewById(R.id.container)

//        onclicklistener here

        fun bind(item: ProductModel, position: Int) {
            title.text = item.title
            duration.text = item.duration
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