package com.solvathon.ui.locator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.solvathon.databinding.FragmentLocatorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocatorFragment : Fragment() {

    private lateinit var locatorViewModel: LocatorViewModel
    private var _binding: FragmentLocatorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        locatorViewModel =
            ViewModelProvider(this).get(LocatorViewModel::class.java)

        _binding = FragmentLocatorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow
        locatorViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}