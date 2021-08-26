package com.solvathon.ui.quotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R

class MyQuotesAdapter(private val quotes: List<QuoteItem>): RecyclerView.Adapter<MyQuotesAdapter.MyQuotesViewHolder>() {

    class MyQuotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val lifeCover: TextView = itemView.findViewById(R.id.text_view_life_cover)
        val age:TextView = itemView.findViewById(R.id.text_view_age)
        val claimSettled:TextView = itemView.findViewById(R.id.text_view_claim_settled)
        val monthlyPayment:TextView = itemView.findViewById(R.id.text_view_monthly_payment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyQuotesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.quote_list_item, parent, false)
        return MyQuotesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyQuotesViewHolder, position: Int) {
        val currentItem = quotes[position]

        holder.lifeCover.text = currentItem.lifeCover
        holder.age.text = currentItem.age
        holder.claimSettled.text = currentItem.claimSettled
        holder.monthlyPayment.text = currentItem.monthlyPayment
    }

    override fun getItemCount()= quotes.size
}