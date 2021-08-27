package com.solvathon.ui.claims

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R

class DocumentAdapter(private val myDocuments:List<String>): RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder>() {

    class DocumentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val doucment:TextView = itemView.findViewById(R.id.text_view_document)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.document,parent,false)
        Log.i("lsit", myDocuments.toString())
        return DocumentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) {
        val currentItem = myDocuments[position]
        holder.doucment.text = currentItem
    }

    override fun getItemCount()=myDocuments.size
}