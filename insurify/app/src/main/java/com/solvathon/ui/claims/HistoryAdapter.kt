package com.solvathon.ui.claims

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R

class HistoryAdapter(private val historyList:List<HistoryItem>) :RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(){

    class HistoryViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val statusDate:TextView = itemView.findViewById(R.id.text_view_status_date)
        val imageResource:ImageView = itemView.findViewById(R.id.image_view_checkbox)
        val status:TextView = itemView.findViewById(R.id.text_view_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.history_item,parent,false)
        return HistoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val currentItem = historyList[position]
        holder.statusDate.text = currentItem.statusDate
        holder.status.text = currentItem.status
        holder.imageResource.setImageResource(currentItem.checkbox)
    }

    override fun getItemCount()=historyList.size

}