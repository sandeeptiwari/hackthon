package com.solvathon.ui.policy
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.domain.pojo.Policy
import android.content.Context
import android.widget.ImageView


class MyPolicyAdapter(private val myPolicyList: ArrayList<Policy>): RecyclerView.Adapter<MyPolicyAdapter.MyPolicyViewHolder> (){
    lateinit var context : Context
    class MyPolicyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //initial layout was diffreent so have kept the same id for testing will change once this works
        val policyId:TextView = itemView.findViewById(R.id.textView13)
        val premium:TextView = itemView.findViewById(R.id.textView14)
        val fees:TextView = itemView.findViewById(R.id.textView15)
        val taxes:TextView = itemView.findViewById(R.id.textView16)
        val insuranceType:TextView = itemView.findViewById(R.id.InsuranceType)
        val viewPolicyBtn: Button = itemView.findViewById(R.id.viewPolicy)
        val image: ImageView = itemView.findViewById(R.id.imageView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPolicyViewHolder {
        context=parent.context
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.policy_item,parent,false)
        return MyPolicyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyPolicyViewHolder, position: Int) {
        val currentItem = myPolicyList[position]
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


        holder.viewPolicyBtn.setOnClickListener {
            val policy:Policy = myPolicyList.get(position)

            val intent = Intent(context, IndividualPolicyActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("INDIVIDUAL_POLICY_DATA", policy)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }


    }

    override fun getItemCount() = myPolicyList.size
}