package com.solvathon.ui.quotes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.domain.pojo.Policy
import com.solvathon.ui.home.HomeFragment
import com.solvathon.ui.policy.IndividualPolicyActivity
import com.google.android.material.snackbar.Snackbar
import com.solvathon.MainActivity


class MyQuotesAdapter(private val quotes: List<Policy>): RecyclerView.Adapter<MyQuotesAdapter.MyQuotesViewHolder>() {
    lateinit var context : Context
    class MyQuotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //initial layout was diffreent so have kept the same id for testing will change once this works
        val policyId:TextView = itemView.findViewById(R.id.quoteId)
        val premium:TextView = itemView.findViewById(R.id.quotePremium)
        val fees:TextView = itemView.findViewById(R.id.quoteFees)
        val taxes:TextView = itemView.findViewById(R.id.quoteFees)
        val insuranceType:TextView=itemView.findViewById(R.id.insuranceType)
        val viewQuoteBtn: Button = itemView.findViewById(R.id.buyPolicy)
        val image: ImageView = itemView.findViewById(R.id.imageView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyQuotesViewHolder {
        context=parent.context
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

        if(holder.insuranceType.text=="Health Insurance") {
            holder.image.setImageResource(R.drawable.ambulance)
        }else if(holder.insuranceType.text=="Car Insurance"){
            holder.image.setImageResource(R.drawable.car_24)
        }else if(holder.insuranceType.text=="2 Wheeler Insurance"){
            holder.image.setImageResource(R.drawable.bike_24)
        }else if(holder.insuranceType.text=="Travel Insurance"){
            holder.image.setImageResource(R.drawable.travel_24)
        }else{
            holder.image.setImageResource(R.drawable.book_appointment)
        }

        holder.viewQuoteBtn.setOnClickListener {

            val intent = Intent(context, MainActivity::class.java)

            val text = "Bought Policy!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(context, text, duration)
            toast.show()
            context.startActivity(intent)


        }
    }

    override fun getItemCount()= quotes.size
}