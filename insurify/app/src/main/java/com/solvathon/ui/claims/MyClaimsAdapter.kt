package com.solvathon.ui.claims

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R

class MyClaimsAdapter(private val myClaimsList: List<ClaimItem>): RecyclerView.Adapter<MyClaimsAdapter.MyClaimsViewHolder> (){

    class MyClaimsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name:TextView = itemView.findViewById(R.id.text_view_name)
        val claimId:TextView = itemView.findViewById(R.id.text_view_claim_id)
        val claimAmount:TextView = itemView.findViewById(R.id.text_view_claim_amount)
        val dateOfAdmission:TextView = itemView.findViewById(R.id.text_view_date_of_admission)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyClaimsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.my_claims_item,parent,false)
        return MyClaimsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyClaimsViewHolder, position: Int) {
        val currentItem = myClaimsList[position]
        holder.name.text = currentItem.name
        holder.claimId.text = currentItem.claimId
        holder.dateOfAdmission.text = currentItem.dateOfAdmission
        holder.claimAmount.text = currentItem.claimAmount
    }

    override fun getItemCount() = myClaimsList.size
}