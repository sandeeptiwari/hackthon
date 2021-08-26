package com.solvathon.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.solvathon.R
import com.solvathon.ui.base.BaseFragment
import com.visbiliti.exception.NoDataException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("OnCreatetag", "ExploreOnCreate")
        //observePolicyResponse()
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

}