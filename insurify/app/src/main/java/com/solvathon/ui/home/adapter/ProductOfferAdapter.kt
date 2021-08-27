package com.solvathon.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.databinding.OfferItemBinding
import com.solvathon.databinding.RowLobsBinding
import com.solvathon.databinding.RowOfferBinding
import com.solvathon.domain.pojo.Policy
import com.solvathon.domain.pojo.Product

class ProductOfferAdapter (var offerProducts: List<Product>,
                           private val onItemClickListener: OnOfferItemClickListener):
    RecyclerView.Adapter<ProductOfferAdapter.ProductOfferViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductOfferViewHolder {
        val itemBinding =
            RowOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductOfferViewHolder(itemBinding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ProductOfferViewHolder, position: Int) {
        val product = offerProducts.get(position)
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        Log.i("PolicyAdapter","userList>userFollowerList" + offerProducts.size)
        return offerProducts.size
    }

    class ProductOfferViewHolder(
        private val itemBinding: RowOfferBinding,
        val onItemClickListener: OnOfferItemClickListener
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(product: Product) {
            itemBinding.root.setOnClickListener {
                onItemClickListener.onOfferItemClick(bindingAdapterPosition)
            }
        }
    }

    interface OnOfferItemClickListener {
        fun onOfferItemClick(pos: Int)
    }
}