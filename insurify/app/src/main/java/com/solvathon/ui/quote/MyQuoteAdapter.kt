package com.solvathon.ui.policy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R

class MyQuoteAdapter(private val myQuoteList: List<QuoteItem>): RecyclerView.Adapter<MyQuoteAdapter.MyQuoteViewHolder> (){

    class MyQuoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val lifeCover:TextView = itemView.findViewById(R.id.lifecover)
        val claimSettled:TextView = itemView.findViewById(R.id.claimsettled)
        val claimAmount:TextView = itemView.findViewById(R.id.monthlypay)
        val age:TextView = itemView.findViewById(R.id.age)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyQuoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.policy,parent,false)
        return MyQuoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyQuoteViewHolder, position: Int) {
        val currentItem = myQuoteList[position]
        holder.lifeCover.text = currentItem.lifeCover
        holder.claimAmount.text = currentItem.monthlyPay
        holder.age.text = currentItem.age
        holder.claimSettled.text = currentItem.claimSettled
    }

    override fun getItemCount() = myQuoteList.size
}