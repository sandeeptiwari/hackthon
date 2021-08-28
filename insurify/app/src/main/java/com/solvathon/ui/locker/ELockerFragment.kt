package com.solvathon.ui.locker

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.solvathon.R
import com.solvathon.databinding.FragmentElockerBinding
import com.solvathon.ui.base.BaseFragment
import com.solvathon.ui.locker.activity.UploadDocumentActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ELockerFragment : BaseFragment() {

    private lateinit var elockerViewModel: ELockerViewModel
    private var _binding: FragmentElockerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun bindData() {
        binding.policyDocCarView.setOnClickListener {
            val intent = Intent(activity, UploadDocumentActivity::class.java)
            val bundle = Bundle()
            bundle.putString("DOC_TYPE", "Policy ")
            intent.putExtras(bundle)
            startActivity(intent)
        }
        binding.medicalDocCarView.setOnClickListener {
            val intent = Intent(activity, UploadDocumentActivity::class.java)
            val bundle = Bundle()
            bundle.putString("DOC_TYPE", "Medical ")
            intent.putExtras(bundle)
            startActivity(intent)
        }
        binding.idandAddressDocCarView.setOnClickListener {
            val intent = Intent(activity, UploadDocumentActivity::class.java)
            val bundle = Bundle()
            bundle.putString("DOC_TYPE", "ID And Address ")
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    override fun createLayout(): Int {
        return R.layout.fragment_elocker
    }

    override fun bindViewWithViewBinding(view: View) {
        _binding = FragmentElockerBinding.bind(view)
    }

    override fun showLoader(b: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}