package com.example.quakeapplication.di

import android.content.Context
import com.example.quakeapplication.MyApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
                      AndroidSupportInjectionModule::class,
                      QuakesFragmentsModule::class,
                      StatisticsFragmentModule::class
                    ])
interface AppComponent : AndroidInjector<MyApp>{

    @Component.Factory
    interface Factory {
         fun create(@BindsInstance applicationContext: Context): AppComponent
    }

}
