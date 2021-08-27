package com.solvathon.ui.claims

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R

class MyClaimsAdapter(private val myClaimsList: List<ClaimItem>): RecyclerView.Adapter<MyClaimsAdapter.MyClaimsViewHolder> (){

    lateinit var context: Context
    class MyClaimsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name:TextView = itemView.findViewById(R.id.text_view_name)
        val claimId:TextView = itemView.findViewById(R.id.text_view_claim_id)
        val claimAmount:TextView = itemView.findViewById(R.id.text_view_claim_amount)
        val dateOfAdmission:TextView = itemView.findViewById(R.id.text_view_date_of_admission)
        val viewClaimBtn:Button = itemView.findViewById(R.id.button_view_claim)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyClaimsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.my_claims_item,parent,false)
        context = parent.context
        return MyClaimsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyClaimsViewHolder, position: Int) {
        val currentItem = myClaimsList[position]
        holder.name.text = currentItem.name
        holder.claimId.text = currentItem.claimId
        holder.dateOfAdmission.text = currentItem.dateOfAdmission
        holder.claimAmount.text = currentItem.claimAmount
        holder.viewClaimBtn.setOnClickListener {
            val claim:ClaimItem = myClaimsList.get(position)
            val intent: Intent = Intent(context, ClaimActivity::class.java)
            intent.putExtra("name",claim.name)
            intent.putExtra("claimId",claim.claimId)
            intent.putExtra("dateOfAdmission",claim.dateOfAdmission)
            intent.putExtra("claimAmount",claim.claimAmount)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = myClaimsList.size
}