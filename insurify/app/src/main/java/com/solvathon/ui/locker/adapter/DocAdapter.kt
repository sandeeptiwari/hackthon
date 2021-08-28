package com.solvathon.ui.locker.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.databinding.DocItemBinding
import com.solvathon.domain.pojo.Doc

class DocAdapter (private val onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<DocAdapter.DocViewHolder>() {
    val docs: ArrayList<Doc> = ArrayList()

    fun add(doc: Doc) {
       docs.add(doc)
       notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocViewHolder {
        val itemBinding =
            DocItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DocViewHolder(itemBinding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: DocViewHolder, position: Int) {
        val doc = docs.get(position)
        holder.bind(doc)
    }

    override fun getItemCount(): Int {
        Log.i("DocAdapter","Doc > doc" + docs.size)
        return docs.size
    }

    class DocViewHolder(
        private val itemBinding: DocItemBinding,
        val onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(doc: Doc) {
            itemBinding.docName.text = doc.name
            itemBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(bindingAdapterPosition)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(pos: Int)
    }
}