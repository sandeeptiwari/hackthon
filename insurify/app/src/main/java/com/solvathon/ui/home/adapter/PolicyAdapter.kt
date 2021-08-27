package com.solvathon.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.databinding.RowLobsBinding
import com.solvathon.domain.pojo.Policy
import androidx.appcompat.widget.AppCompatTextView
import com.solvathon.R

class PolicyAdapter (var policies: List<Policy>,
                     val groupByInsuranceType: Map<kotlin.String, List<Policy>>,
                     private val onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<PolicyAdapter.PolicyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PolicyViewHolder {
        val itemBinding =
            RowLobsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PolicyViewHolder(itemBinding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: PolicyViewHolder, position: Int) {
        val policy = policies.get(position)
        holder.bind(policy, groupByInsuranceType)
    }

    override fun getItemCount(): Int {
        Log.i("PolicyAdapter","userList>userFollowerList" +policies.size)
        return policies.size
    }

    class PolicyViewHolder(
        private val itemBinding: RowLobsBinding,
        val onItemClickListener: OnItemClickListener
    ) :RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(policy: Policy, groupByInsuranceType: Map<kotlin.String, List<Policy>>) {
            itemBinding.tvPolicyName.text = policy.insuranceType
            if (policy.insuranceType.indexOf("Health") >= 0) {
                itemBinding.tvPolicyNumber.setImageResource(R.drawable.health_24)
            } else if (policy.insuranceType.indexOf("Car") >= 0) {
                itemBinding.tvPolicyNumber.setImageResource(R.drawable.car_24)
            } else if (policy.insuranceType.indexOf("Wheeler") >= 0) {
                itemBinding.tvPolicyNumber.setImageResource(R.drawable.bike_24)
            } else {
                itemBinding.tvPolicyNumber.setImageResource(R.drawable.travel_24)
            }
            val policyCount: AppCompatTextView = itemBinding.tvPolicyCount
            val count = groupByInsuranceType.get(policy.insuranceType)?.size;
            policyCount.text = count.toString()

            itemBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(bindingAdapterPosition)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(pos: Int)
    }
}