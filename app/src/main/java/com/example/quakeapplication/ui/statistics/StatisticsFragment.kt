package com.example.quakeapplication.ui.statistics

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
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

        setHasOptionsMenu(true)

        binding = DataBindingUtil.inflate<FragmentStatisticsBinding>(inflater, R.layout.fragment_statistics, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            statisticViewModel = viewModel
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_filter -> {
                showPopupMenu()
                true
            }
            else ->  false
        }
    }

    private fun showPopupMenu() {
        val view = requireActivity().findViewById<View>(R.id.action_filter) ?: return

        PopupMenu(activity, view).run {
            inflate(R.menu.filter_statistic_menu)

            setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId) {
                    R.id.item_magnitudeForWeek -> viewModel.setFilter(StatisticFilterType.MAGNITUDE_COUNT_FOR_WEEK)
                    R.id.item_magnitudeForMonth -> viewModel.setFilter(StatisticFilterType.MAGNITUDE_COUNT_FOR_MONTH)
                    R.id.item_magnitudeForYear -> viewModel.setFilter(StatisticFilterType.MAGNITUDE_COUNT_FOR_YEAR)
                }
                viewModel.getStatistic()
                true
            }

            show()
        }
    }
}