package com.solvathon.ui.policy

import com.solvathon.ui.policy.PolicyItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import org.w3c.dom.Text

class MyPolicyAdapter(private val myPolicyList: List<PolicyItem>): RecyclerView.Adapter<MyPolicyAdapter.MyPolicyViewHolder> (){

    class MyPolicyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val policyType:TextView=itemView.findViewById(R.id.policyType)
        val lifeCover:TextView = itemView.findViewById(R.id.lifecover)
        val claimSettled:TextView = itemView.findViewById(R.id.claimsettled)
        val claimAmount:TextView = itemView.findViewById(R.id.monthlypay)
        val age:TextView = itemView.findViewById(R.id.age)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPolicyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.policy,parent,false)
        return MyPolicyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyPolicyViewHolder, position: Int) {
        val currentItem = myPolicyList[position]
        holder.lifeCover.text = currentItem.lifeCover
        holder.claimAmount.text = currentItem.monthlyPay
        holder.age.text = currentItem.age
        holder.claimSettled.text = currentItem.claimSettled
        holder.policyType.text=currentItem.policyType
    }

    override fun getItemCount() = myPolicyList.size
}