package com.solvathon.ui.locator

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.databinding.FragmentLocatorBinding
import com.solvathon.databinding.HomeFragmentBinding
import com.solvathon.domain.pojo.DashBoardMenu
import com.solvathon.domain.pojo.NetworkLocator
import com.solvathon.domain.pojo.Policy
import com.solvathon.domain.pojo.Product
import com.solvathon.ui.base.BaseFragment
import com.solvathon.ui.home.HomeFragment
import com.solvathon.ui.home.adapter.PolicyLobsAdapter
import com.solvathon.ui.locator.adapter.OurNetworkAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigDecimal
import java.time.LocalDate
import android.R.attr.y

import android.R.attr.x
import android.content.Context
import android.location.LocationManager
import android.net.Uri
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.gms.location.*
import com.solvathon.di.InsurifyApp.Companion.mContext


@AndroidEntryPoint
class LocatorFragment : BaseFragment(), OurNetworkAdapter.OnItemClickListener {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var locatorViewModel: LocatorViewModel
    private var _binding: FragmentLocatorBinding? = null
    private var lat: Double? = 0.0
    private var lon: Double? = 0.0;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var linearLayoutManager: LinearLayoutManager

    companion object {
        fun newInstance() = LocatorFragment()
    }

    var networkLocators = listOf(
        NetworkLocator(
            "Section", "", "Find Our Network Locators", -1
        ),
        NetworkLocator(
            "Network", "Hospitals", "Hospitals", R.drawable.hospitals
        ),
        NetworkLocator(
            "Network", "Garages", "Garages", R.drawable.garage_24
        ),
        NetworkLocator(
            "Network", "Branches", "Branches", R.drawable.branch
        ),
        NetworkLocator(
            "Section", "Find Nearest", "Find Nearest", -1
        ),
        NetworkLocator(
            "Network", "Ambulances", "Ambulances", R.drawable.ambulance
        ),
        NetworkLocator(
            "Network", "Spas", "Spas", R.drawable.spa
        ),
        NetworkLocator(
            "Network", "ATM", "ATM", R.drawable.local_atm_24
        ),
        NetworkLocator(
            "Network", "Gyms", "Gyms", R.drawable.gym
        ),
        NetworkLocator(
            "Network", "Pharmacies", "Pharmacies", R.drawable.local_pharmacy_24
        ),
        NetworkLocator(
            "Network", "Diagnostics", "Diagnostics", R.drawable.diagnostic
        )

    )

    val locatorListAdapter by lazy {
        OurNetworkAdapter(networkLocators, this)
    }

    private fun setUpNetworkLocatorRecyclerView() {
        Log.d("tag", "userList>" + networkLocators.size)
        linearLayoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)

        binding.rvLocator.apply {
            layoutManager = linearLayoutManager
            adapter = locatorListAdapter
        }
        locatorListAdapter?.notifyDataSetChanged()
    }

    override fun bindData() {
        setUpNetworkLocatorRecyclerView();
        checkLocation()
    }

    private fun checkLocation(){
        val manager = mContext?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext)
        getLocationUpdates()
    }
    private fun getLocationUpdates() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        locationRequest = LocationRequest()
        locationRequest.interval = 50000
        locationRequest.fastestInterval = 50000
        locationRequest.smallestDisplacement = 170f //170 m = 0.1 mile
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                lat = locationResult?.lastLocation?.latitude
                lon = locationResult?.lastLocation?.longitude
            }
        }
    }



        override fun createLayout(): Int {
        return  R.layout.fragment_locator
    }

    override fun bindViewWithViewBinding(view: View) {
        _binding = FragmentLocatorBinding.bind(view)
    }

    override fun showLoader(b: Boolean) {
        TODO("Not yet implemented")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(pos: Int) {
        when(pos) {
            1 -> {
                val uri = "geo:" + lat + "," + lon + "&q=Hospitals"
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
            }
            2-> {
                val uri = "geo:" + lat + "," + lon + "&q=garage"
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
            }
            5-> {
                val uri = "geo:" + lat + "," + lon + "&q=ambulance"
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
            }
            7-> {
                val uri = "geo:" + lat + "," + lon + "&q=ATMs"
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
            }
            else -> {
                val intent = Intent(activity, NearByActivity::class.java)
                startActivity(intent)
            }
        }

    }
}