package com.example.quakeapplication.domain.quakes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quakeapplication.R
import com.example.quakeapplication.databinding.FragmentQuakesBinding

class QuakesFragment : Fragment() {

    private lateinit var viewModel: QuakesViewModel
    private lateinit var binding: FragmentQuakesBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quakes, container, false)

        viewModel = ViewModelProvider(this).get(QuakesViewModel::class.java)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            quakesViewModel = viewModel
        }

        return binding.root
    }
}