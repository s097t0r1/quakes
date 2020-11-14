package com.example.quakeapplication.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.quakeapplication.R
import com.example.quakeapplication.databinding.FragmentDetailQuakeBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class DetailsFragment : DaggerFragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: DetailsViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentDetailQuakeBinding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_quake,
                                          container, false
        )



        return binding.root
    }




}