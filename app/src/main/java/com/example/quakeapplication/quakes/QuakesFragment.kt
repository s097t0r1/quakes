package com.example.quakeapplication.quakes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quakeapplication.R
import com.example.quakeapplication.data.Quake
import com.example.quakeapplication.databinding.FragmentQuakesBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class QuakesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: QuakesViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentQuakesBinding


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentQuakesBinding>(inflater, R.layout.fragment_quakes, container, false).apply {
            quakesViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
        setupErrorHandlers()

    }

    private fun setupErrorHandlers() {
        viewModel.errorLoadingData.observe(viewLifecycleOwner, Observer { errorLoadingData ->
            if(errorLoadingData) {
                Toast.makeText(context, R.string.error_loading_data_message, Toast.LENGTH_SHORT).show()
                viewModel.onErrorLoadingDataComplete()
            }

        })
    }

    private fun setupRecyclerView() {

        val adapter = QuakesAdapter()

        viewModel.quakesList.observe(viewLifecycleOwner, Observer { quakeList: List<Quake> ->
            adapter.submitList(quakeList)
        })

        binding.recyclerViewQuakeList.adapter = adapter
    }



}