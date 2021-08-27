package com.solvathon.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.databinding.RowDashboardMenuBinding
import com.solvathon.domain.pojo.DashBoardMenu

class HomeMenuAdapter (var menus: List<DashBoardMenu>,
                       private val onMenuClickListener: OnMenulickListener):
    RecyclerView.Adapter<HomeMenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val itemBinding =
            RowDashboardMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(itemBinding, onMenuClickListener)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = menus.get(position)
        holder.bind(menu)
    }

    override fun getItemCount(): Int {
        Log.i("HomeMenuAdapter","userList>userFollowerList" + menus.size)
        return menus.size
    }

    class MenuViewHolder(
        private val itemBinding: RowDashboardMenuBinding,
        val onMenuClickListener: OnMenulickListener
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(menu: DashBoardMenu) {
            itemBinding.menuIcon.setImageResource(menu.id)
            itemBinding.menuTitle.text = menu.name
            itemBinding.root.setOnClickListener {
                onMenuClickListener.onMenuClick(bindingAdapterPosition)
            }
        }
    }

    interface OnMenulickListener {
        fun onMenuClick(pos: Int)
    }
}