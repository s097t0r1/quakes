package com.example.quakeapplication.di

import androidx.lifecycle.ViewModel
import com.example.quakeapplication.ui.quakes.QuakesFragment
import com.example.quakeapplication.ui.quakes.QuakesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class QuakesFragmentsModule() {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun quakesFragment(): QuakesFragment

    @Binds
    @IntoMap
    @ViewModelKey(QuakesViewModel::class)
    abstract fun bindViewModel(viewModel: QuakesViewModel): ViewModel

}