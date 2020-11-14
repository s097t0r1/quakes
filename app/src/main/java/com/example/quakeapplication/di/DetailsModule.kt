package com.example.quakeapplication.di

import androidx.lifecycle.ViewModel
import com.example.quakeapplication.ui.details.DetailsFragment
import com.example.quakeapplication.ui.details.DetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DetailsFragmentModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun detailsFragment(): DetailsFragment

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindViewModel(viewModel: DetailsViewModel): ViewModel

}