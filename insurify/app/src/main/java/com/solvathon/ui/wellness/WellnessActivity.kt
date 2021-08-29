package com.solvathon.ui.wellness

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.databinding.ActivityWellnessBinding
import com.solvathon.domain.pojo.DashBoardMenu
import com.solvathon.domain.pojo.Product
import com.solvathon.ui.base.BaseActivity
import com.solvathon.ui.home.adapter.HomeMenuAdapter
import com.solvathon.ui.home.adapter.ProductOfferAdapter

class WellnessActivity() : BaseActivity() {
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var binding: ActivityWellnessBinding

    var products = listOf( Product(1, "Best health Insurance", R.drawable.offer1),
        Product(2, "Car Insurance", R.drawable.offer2),
        Product(3, "Travel Insurance", R.drawable.offer3),
        Product(3, "2 Wheeler Insurance", R.drawable.offer4)
    )

    var menus = listOf(
        DashBoardMenu("Health\n Card", R.drawable.health_card),
        DashBoardMenu("Book \nAppointment", R.drawable.book_appointment),
        DashBoardMenu("OPD\n Cashless", R.drawable.opd_cashless),
        DashBoardMenu("OPD\n Reimbursement", R.drawable.opd_reimbursement),
        DashBoardMenu("Online\n Counselling", R.drawable.online_counselling),
        DashBoardMenu("Health Help", R.drawable.health_help),
        DashBoardMenu("Diet Plan", R.drawable.diet_plan),
        DashBoardMenu("Physical\n Activity", R.drawable.physical_activity),
        DashBoardMenu("Health\n Calculators", R.drawable.health_calculator)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)

        setUpOfferRecyclerView()
        setUpMenuRecyclerView()
    }

    private fun setUpOfferRecyclerView() {
//        Log.d("tag", "userList>" + policies.size)
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        binding.idOflayoutOffers.rvOffers.apply {
            layoutManager = linearLayoutManager
            adapter = ProductOfferAdapter(products, OnOfferItemClickListenerImpl() )
        }

    }

    private fun setUpMenuRecyclerView() {
//        Log.d("tag", "menus>" + menus.size)
        gridLayoutManager = GridLayoutManager(this, 3)
        binding.idOflayoutQuickAccess.rvCategories.setHasFixedSize(true)
        binding.idOflayoutQuickAccess.rvCategories.apply {
            layoutManager = gridLayoutManager
            adapter = HomeMenuAdapter(menus, OnMenuClickListenerImpl())
        }

    }

    override fun findContentView(): Int {
        return R.layout.activity_wellness
    }

    override fun bindViewWithViewBinding(view: View) {
        binding = ActivityWellnessBinding.bind(view)
    }

    class OnOfferItemClickListenerImpl : ProductOfferAdapter.OnOfferItemClickListener{
        override fun onOfferItemClick(pos: Int) {
            TODO("Not yet implemented")
        }

    }

    class OnMenuClickListenerImpl: HomeMenuAdapter.OnMenulickListener{
        override fun onMenuClick(pos: Int) {
            TODO("Not yet implemented")
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}