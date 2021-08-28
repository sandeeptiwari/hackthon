package com.solvathon.ui.more

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.databinding.FragmentElockerBinding
import com.solvathon.databinding.MoreFragmentBinding
import com.solvathon.domain.pojo.More
import com.solvathon.domain.pojo.Policy
import com.solvathon.ui.base.BaseFragment
import com.solvathon.ui.home.adapter.ProductOfferAdapter
import com.solvathon.ui.more.adapter.MoreAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigDecimal
import java.time.LocalDate

@AndroidEntryPoint
class MoreFragment : BaseFragment(), MoreAdapter.OnItemClickListener {
    lateinit var linearLayoutManager: LinearLayoutManager
    private var _binding: MoreFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MoreFragment()
    }

    private lateinit var viewModel: MoreViewModel

    var mores = listOf(
        More(
            1000, "Profile", R.drawable.profile
        ),
        More(
            1000, "Track Policy Request", R.drawable.track_24
        ),
        More(
            1000, "Emergency", R.drawable.emergency_24
        ),
        More(
            1000, "FeedBack", R.drawable.feedback_24
        ),
        More(
            1000, "About Insurify", R.drawable.about_us
        ),
        More(
            1000, "Logout", R.drawable.logout_24
        )
    )

    val moreListAdapter by lazy {
        MoreAdapter(mores, this)
    }


    override fun bindData() {
        setUpRecyclerView()
    }

    override fun createLayout(): Int {
        return R.layout.more_fragment
    }

    override fun bindViewWithViewBinding(view: View) {
        _binding = MoreFragmentBinding.bind(view)
    }

    override fun showLoader(b: Boolean) {
    }

    override fun onItemClick(pos: Int) {
        TODO("Not yet implemented")
    }

    private fun setUpRecyclerView() {
        Log.d("MoreFragment", "More List >" + mores.size)
        linearLayoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)

        binding.rvMoreFragment.apply {
            layoutManager = linearLayoutManager
            adapter = moreListAdapter
        }
        moreListAdapter?.notifyDataSetChanged()
    }
}