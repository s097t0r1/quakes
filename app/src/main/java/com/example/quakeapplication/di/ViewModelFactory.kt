package com.example.quakeapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class ViewModelFactory @Inject constructor(
    val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        var creator: Provider<ViewModel>? = creators[modelClass]

        if(creator == null) {

            for((key, value) in creators) {

                if(modelClass.isAssignableFrom(key)) {
                    creator = value
                }

            }

        }

        if(creator == null)
            throw IllegalArgumentException("Unknown model class: $modelClass")

        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw TypeCastException(e.message)
        }
    }
}

@Module
abstract class ViewModelBuilder {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val viewModelClass: KClass<out ViewModel>)