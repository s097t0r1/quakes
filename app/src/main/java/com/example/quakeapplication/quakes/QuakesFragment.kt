package com.example.quakeapplication.quakes

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
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
import timber.log.Timber
import javax.inject.Inject
class QuakesFragment : DaggerFragment(), FilterDialogFragment.FilterDialogListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: QuakesViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentQuakesBinding


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_filter -> {
                showFilterDialog()
                true
            }
            else -> false
        }
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

        binding.recyclerViewQuakeList.adapter = adapter

        viewModel.quakesList.observe(viewLifecycleOwner, Observer { quakeList: List<Quake> ->
            adapter.submitList(quakeList)
        })

    }

    private fun showFilterDialog() {
        val filterFragment = FilterDialogFragment()
        filterFragment.show(childFragmentManager, "FilterDialogFragment")
    }

    override fun onDialogPositiveClick(seekBarProgress: Int) {
        Timber.d(seekBarProgress.toString())
        viewModel.setFilterMMI(seekBarProgress)
    }

    override fun onDialogNegativeClick() {
        Timber.d("Negative click")
    }


}

