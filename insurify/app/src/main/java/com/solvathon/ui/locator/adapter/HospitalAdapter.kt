package com.solvathon.ui.locator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.ui.locator.Hospital

class HospitalAdapter(private var hospitals:List<Hospital>) :RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder>(){

    class HospitalViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name:TextView = itemView.findViewById(R.id.text_view_name)
        val address:TextView = itemView.findViewById(R.id.hospital_address)
        val phone:TextView = itemView.findViewById(R.id.phone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        val itemView:View = LayoutInflater.from(parent.context).inflate(R.layout.hospital_item,parent,false)
        return HospitalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
       val currentItem = hospitals[position]
        holder.address.text = currentItem.address
        holder.name.text = currentItem.name
        holder.phone.text = currentItem.phone
    }

    override fun getItemCount()= hospitals.size
}