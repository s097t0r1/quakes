package com.example.quakeapplication.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.quakeapplication.R
import com.example.quakeapplication.databinding.FragmentStatisticsBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class StatisticsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: StatisticsViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentStatisticsBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistics, container, false)

        viewModel.test()

        return binding.root
    }
}