package com.solvathon.ui.locator.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.databinding.ItemLocatorsBinding
import com.solvathon.domain.pojo.NetworkLocator

class OurNetworkAdapter(
    var networkLocators: List<NetworkLocator>,
    private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<OurNetworkAdapter.OurNetworkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OurNetworkViewHolder {
        val itemLocatorBinding =
            ItemLocatorsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OurNetworkViewHolder(itemLocatorBinding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: OurNetworkViewHolder, position: Int) {
        val ourNetwork = networkLocators.get(position)
        holder.bind(ourNetwork)
    }

    override fun getItemCount(): Int {
        Log.i("OurNetworkAdapter", "userList>userFollowerList" + networkLocators.size)
        return networkLocators.size
    }

    class OurNetworkViewHolder(
        private val itemLocatorsBinding: ItemLocatorsBinding,
        val onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemLocatorsBinding.root) {

        fun bind(networkLocator: NetworkLocator) {
            /*itemLocatorsBinding.locatorCarView.visibility = View.VISIBLE
            itemLocatorsBinding.locatorIcon.setImageResource(networkLocator.icon)
            itemLocatorsBinding.locatorTitle.text = networkLocator.networkName*/
            when(networkLocator.itemType) {
                "Section" -> {
                    itemLocatorsBinding.locatorCarView.visibility = View.GONE
                    itemLocatorsBinding.itemSection.root.visibility = View.VISIBLE
                    itemLocatorsBinding.itemSection.sectionTitle.text = networkLocator.networkName
                }
                "Network" -> {
                    itemLocatorsBinding.locatorCarView.visibility = View.VISIBLE
                    itemLocatorsBinding.itemSection.root.visibility = View.GONE
                    itemLocatorsBinding.locatorIcon.setImageResource(networkLocator.icon)
                    itemLocatorsBinding.locatorTitle.text = networkLocator.networkName
                }
            }

            itemLocatorsBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(bindingAdapterPosition)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(pos: Int)
    }
}