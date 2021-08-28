package com.solvathon.ui.wellness

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.databinding.ActivityWellnessBinding
import com.solvathon.domain.pojo.Product
import com.solvathon.ui.base.BaseActivity
import com.solvathon.ui.home.adapter.ProductOfferAdapter

class WellnessActivity() : BaseActivity() {
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var binding: ActivityWellnessBinding

    var products = listOf( Product(1, "Best health Insurance", R.drawable.offer1),
        Product(2, "Car Insurance", R.drawable.offer2),
        Product(3, "Travel Insurance", R.drawable.offer3),
        Product(3, "2 Wheeler Insurance", R.drawable.offer4)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)

        setUpOfferRecyclerView()
    }

    private fun setUpOfferRecyclerView() {
//        Log.d("tag", "userList>" + policies.size)
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        binding.idOflayoutOffers.rvOffers.apply {
            layoutManager = linearLayoutManager
            adapter = ProductOfferAdapter(products, OnOfferItemClickListenerImpl() )
        }

    }

    override fun findContentView(): Int {
        return R.layout.activity_wellness
    }

    override fun bindViewWithViewBinding(view: View) {
        binding = ActivityWellnessBinding.bind(view)
    }


    override fun toggleLoader(b: Boolean) {
        TODO("Not yet implemented")
    }

    class OnOfferItemClickListenerImpl : ProductOfferAdapter.OnOfferItemClickListener{
        override fun onOfferItemClick(pos: Int) {
            TODO("Not yet implemented")
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}