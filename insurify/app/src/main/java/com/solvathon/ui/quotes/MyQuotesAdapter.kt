package com.solvathon.ui.quotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.domain.pojo.Policy

class MyQuotesAdapter(private val quotes: List<Policy>): RecyclerView.Adapter<MyQuotesAdapter.MyQuotesViewHolder>() {

    class MyQuotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //initial layout was diffreent so have kept the same id for testing will change once this works
        val policyId:TextView = itemView.findViewById(R.id.quoteId)
        val premium:TextView = itemView.findViewById(R.id.quotePremium)
        val fees:TextView = itemView.findViewById(R.id.quoteFees)
        val taxes:TextView = itemView.findViewById(R.id.quoteFees)
        val insuranceType:TextView=itemView.findViewById(R.id.insuranceType)
        val viewQuoteBtn: Button = itemView.findViewById(R.id.buyPolicy)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyQuotesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.quotes_item, parent, false)
        return MyQuotesViewHolder(itemView) 
    }

    override fun onBindViewHolder(holder: MyQuotesViewHolder, position: Int) {
        val currentItem = quotes[position]

        holder.policyId.text = currentItem.policyId.toString()
        holder.premium.text = currentItem.premium.toString()
        holder.fees.text = currentItem.fees.toString()
        holder.taxes.text = currentItem.taxes.toString()
        holder.insuranceType.text=currentItem.insuranceType.toString()
    }

    override fun getItemCount()= quotes.size
}