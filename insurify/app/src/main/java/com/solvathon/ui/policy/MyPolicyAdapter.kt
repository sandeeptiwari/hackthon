package com.solvathon.ui.policy

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.domain.pojo.Policy

class MyPolicyAdapter(private val myPolicyList: List<Policy>): RecyclerView.Adapter<MyPolicyAdapter.MyPolicyViewHolder> (){

    class MyPolicyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //initial layout was diffreent so have kept the same id for testing will change once this works
        val policyId:TextView = itemView.findViewById(R.id.lifecover)
        val premium:TextView = itemView.findViewById(R.id.claimsettled)
        val fees:TextView = itemView.findViewById(R.id.monthlypay)
        val taxes:TextView = itemView.findViewById(R.id.age)
        val viewPolicyBtn: Button = itemView.findViewById(R.id.viewPolicy)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPolicyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.policy,parent,false)
        return MyPolicyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyPolicyViewHolder, position: Int) {
        val currentItem = myPolicyList[position]
        holder.policyId.text = currentItem.policyId.toString()
        holder.premium.text = currentItem.premium.toString()
        holder.fees.text = currentItem.fees.toString()
        holder.taxes.text = currentItem.taxes.toString()
/*
will page all the policy info to individual policy screen from here
        holder.viewPolicyBtn.setOnClickListener {
            val policy:Policy = myPolicyList.get(position)
            val intent: Intent = Intent(context, PolicyActivity::class.java).also {
                it.putExtra("name",policy.name)
                it.putExtra("claimId",policy.claimId)
                it.putExtra("dateOfAdmission",policy.dateOfAdmission)
                it.putExtra("claimAmount",policy.claimAmount)
            }
            context.startActivity(intent)
        }
        */

    }

    override fun getItemCount() = myPolicyList.size
}