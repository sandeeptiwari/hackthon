package com.solvathon.ui.claims

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R

class BillingAdapter(private val bills : List<BillingItem>) : RecyclerView.Adapter<BillingAdapter.BillingViewHolder>(){

    class BillingViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val title:TextView = itemView.findViewById(R.id.text_view_bill_title)
        val nonPayable:TextView = itemView.findViewById(R.id.text_view_non_payable)
        val payable:TextView = itemView.findViewById(R.id.text_view_payable)
        val amount:TextView = itemView.findViewById(R.id.text_view_amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillingViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.billing_info, parent, false)
        return BillingViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BillingViewHolder, position: Int) {
        val currentItem = bills[position]

        holder.amount.text = currentItem.amount
        holder.nonPayable.text = currentItem.nonPayable
        holder.payable.text = currentItem.payable
        holder.title.text = currentItem.title
    }

    override fun getItemCount() = bills.size
}