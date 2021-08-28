package com.solvathon.ui.more.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.databinding.ItemMoreBinding
import com.solvathon.domain.pojo.More

class MoreAdapter(var moreItems: List<More>,
                  private val onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<MoreAdapter.MoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreViewHolder {
        val itemBinding =
            ItemMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoreViewHolder(itemBinding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: MoreViewHolder, position: Int) {
        val more = moreItems.get(position)
        holder.bind(more)
    }

    override fun getItemCount(): Int {
        Log.i("MoreAdapter","userList>userFollowerList" + moreItems.size)
        return moreItems.size
    }

    class MoreViewHolder(
        private val itemBinding: ItemMoreBinding,
        val onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(more: More) {
            itemBinding.moreItemIcon.setImageResource(more.icon)
            itemBinding.moreTitle.text = more.moreTitle
            itemBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(bindingAdapterPosition)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(pos: Int)
    }
}