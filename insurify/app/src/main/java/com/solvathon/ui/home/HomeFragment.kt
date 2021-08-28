package com.solvathon.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.databinding.HomeFragmentBinding
import com.solvathon.domain.pojo.DashBoardMenu
import com.solvathon.domain.pojo.Policy
import com.solvathon.domain.pojo.Product
import com.solvathon.ui.base.BaseFragment
import com.solvathon.ui.claims.MyClaimsActivity
import com.solvathon.ui.home.adapter.HomeMenuAdapter
import com.solvathon.ui.home.adapter.PolicyLobsAdapter
import com.solvathon.ui.home.adapter.ProductOfferAdapter
import com.solvathon.ui.policy.PolicyActivity
import com.visbiliti.exception.NoDataException
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigDecimal
import java.time.LocalDate

@AndroidEntryPoint
class HomeFragment : BaseFragment(), PolicyLobsAdapter.OnItemClickListener,
    ProductOfferAdapter.OnOfferItemClickListener,
    HomeMenuAdapter.OnMenulickListener {
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var gridLayoutManager: GridLayoutManager
    companion object {
        fun newInstance() = HomeFragment()
    }
    var products = listOf( Product(1, "Best health Insurance", R.drawable.offer1),
        Product(2, "Car Insurance", R.drawable.offer2),
        Product(3, "Travel Insurance", R.drawable.offer3),
        Product(3, "2 Wheeler Insurance", R.drawable.offer4)
    )

    var policies = listOf(
        Policy(
            1000, BigDecimal(1750), BigDecimal(150), BigDecimal(100),
            LocalDate.now(), LocalDate.now(), "Health Insurance", 2000
        ),
        Policy(
            1000, BigDecimal(1750), BigDecimal(150), BigDecimal(100),
            LocalDate.now(), LocalDate.now(), "Health Insurance", 2000
        ),
        Policy(
            1000, BigDecimal(1750), BigDecimal(150), BigDecimal(100),
            LocalDate.now(), LocalDate.now(), "Car Insurance", 2000
        ),
        Policy(
            1000, BigDecimal(1750), BigDecimal(150), BigDecimal(100),
            LocalDate.now(), LocalDate.now(), "2 Wheeler Insurance", 2000
        ),
        Policy(
            1000, BigDecimal(1750), BigDecimal(150), BigDecimal(100),
            LocalDate.now(), LocalDate.now(), "Travel Insurance", 2000
        )
    )
    var menus = listOf(
        DashBoardMenu("Wellness", R.drawable.wellness),
        DashBoardMenu("Claim", R.drawable.claim),
        DashBoardMenu("Buy Now", R.drawable.buynow),
        DashBoardMenu("Renew", R.drawable.renew),
        DashBoardMenu("Risk", R.drawable.risk),
        DashBoardMenu("Quote", R.drawable.offer)
    )

    private var _binding: HomeFragmentBinding? = null

    private val binding: HomeFragmentBinding
        get() = _binding!!

    val policyListAdapter by lazy {
        val distinctBy = policies.distinctBy { it.insuranceType }
        val groupByInsuranceType = policies.groupBy { it.insuranceType }
        PolicyLobsAdapter(distinctBy, groupByInsuranceType, this)
    }

    val productOfferListAdapter by lazy {
        ProductOfferAdapter(products, this)
    }

    val menuGridAdapter by lazy {
        HomeMenuAdapter(menus, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("OnCreatetag", "ExploreOnCreate")
        //observePolicyResponse()
    }

    override fun bindData() {
        setUpRecyclerView()
        setUpOfferRecyclerView()
        setUpMenuRecyclerView()
    }

    override fun createLayout(): Int {
        return R.layout.home_fragment
    }

    override fun bindViewWithViewBinding(view: View) {
        _binding = HomeFragmentBinding.bind(view)
    }
    private fun observePolicyResponse() {
        Log.d("OnCreatetag", "ExploreOnCreateobserveWsResponse")
        homeViewModel.getPolicyLiveData.observe(this,

            { responseBody ->
                Log.d("tag", " responseBody")
                showLoader(false)
            },
            { throwable ->
                Log.d("tag", " throwable")
                showLoader(false)
                when (throwable) {
                    is NoDataException -> {
                    }
                }
                true
            }
        )
    }

    override fun showLoader(b: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(pos: Int) {
        when(pos) {
            0 -> {
                //policy screen : health
            }
        }
    }

    private fun setUpRecyclerView() {
        Log.d("tag", "userList>" + policies.size)
        linearLayoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)

        binding.idOflayoutPolicies.rvPolicies.apply {
            layoutManager = linearLayoutManager
            adapter = policyListAdapter
        }
        policyListAdapter?.notifyDataSetChanged()
    }

    private fun setUpOfferRecyclerView() {
        Log.d("tag", "userList>" + policies.size)
        linearLayoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)

        binding.idOflayoutOffers.rvOffers.apply {
            layoutManager = linearLayoutManager
            adapter = productOfferListAdapter
        }
        productOfferListAdapter?.notifyDataSetChanged()
    }

    private fun setUpMenuRecyclerView() {
        Log.d("tag", "menus>" + menus.size)
        gridLayoutManager = GridLayoutManager(requireActivity(), 3)
        binding.idOflayoutQuickAccess.rvCategories.setHasFixedSize(true)
        binding.idOflayoutQuickAccess.rvCategories.apply {
            layoutManager = gridLayoutManager
            adapter = menuGridAdapter
        }
        menuGridAdapter?.notifyDataSetChanged()
    }

    override fun onOfferItemClick(pos: Int) {
        TODO("Not yet implemented")
    }

    override fun onMenuClick(pos: Int) {
        when(pos) {
            0 -> {
                //wellness
            }
            1 -> {
                val intent = Intent(activity, MyClaimsActivity::class.java)
                startActivity(intent)
            }
            5 -> {
                val intent = Intent(activity, PolicyActivity()::class.java)
                val bundle = Bundle()
                bundle.putParcelableArrayList("POLICY_DATA", ArrayList(policies))
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }
}