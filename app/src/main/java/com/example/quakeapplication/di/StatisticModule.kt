package com.example.quakeapplication.di

import androidx.lifecycle.ViewModel
import com.example.quakeapplication.statistics.StatisticsFragment
import com.example.quakeapplication.statistics.StatisticsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class StatisticsFragmentModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun statisticsFragment(): StatisticsFragment

    @Binds
    @IntoMap
    @ViewModelKey(StatisticsViewModel::class)
    abstract fun bindViewModel(viewModel: StatisticsViewModel): ViewModel

}