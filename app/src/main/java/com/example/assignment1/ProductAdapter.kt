package com.example.assignment1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter (val ProductList : ArrayList<Products>) : RecyclerView.Adapter<ProductAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.CustomViewHolder, position: Int) {
        holder.productImage.setImageResource(ProductList.get(position).image)
        holder.productName.text = ProductList.get(position).name
    }

    override fun getItemCount(): Int {
        return ProductList.size
    }

    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val productImage = itemView.findViewById<ImageView>(R.id.iv_image)
        val productName = itemView.findViewById<TextView>(R.id.tv_name)
    }
}