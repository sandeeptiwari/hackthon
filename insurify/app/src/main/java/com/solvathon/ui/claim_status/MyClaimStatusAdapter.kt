package com.solvathon.ui.policy

import com.solvathon.ui.policy.PolicyItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.ui.claim_status.ClaimStatusItem
import org.w3c.dom.Text

class MyClaimStatusAdapter(private val myClaimStatusList: List<ClaimStatusItem>): RecyclerView.Adapter<MyClaimStatusAdapter.MyClaimStatusViewHolder> (){

    class MyClaimStatusViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val status:TextView=itemView.findViewById(R.id.status)
        val statusDate:TextView = itemView.findViewById(R.id.statusDate)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyClaimStatusViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.claim_status_main,parent,false)
        return MyClaimStatusViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyClaimStatusViewHolder, position: Int) {
        val currentItem = myClaimStatusList[position]
        holder.status.text = currentItem.status
        holder.statusDate.text = currentItem.statusDate

    }

    override fun getItemCount() = myClaimStatusList.size
}