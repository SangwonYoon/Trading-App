package com.example.assignment1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductDeleteAdapter (val ProductList : ArrayList<Products>) : RecyclerView.Adapter<ProductDeleteAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDeleteAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return CustomViewHolder(view).apply{
            itemView.setOnClickListener {
                val curPos : Int = adapterPosition
                val product : Products = productList.get(curPos)
                deleteItem(product)
            }
        }
    }

    override fun onBindViewHolder(holder: ProductDeleteAdapter.CustomViewHolder, position: Int) {
        val uri = ProductList.get(position).image
        holder.productImage.setImageURI(uri)
        holder.productName.text = ProductList.get(position).name
    }

    override fun getItemCount(): Int {
        return ProductList.size
    }

    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val productImage = itemView.findViewById<ImageView>(R.id.iv_image)
        val productName = itemView.findViewById<TextView>(R.id.tv_name)
    }

    fun addItem(product: Products){
        ProductList.add(product)
        notifyDataSetChanged()
    }

    fun deleteItem(product : Products){
        ProductList.remove(product)
        notifyDataSetChanged()
    }
}