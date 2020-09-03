package com.kpa.android.care.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 *    author : kpa
 *    e-mail : billkp@yeah.net
 */
@Singleton
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val provider = creators[modelClass] ?: creators.asIterable().firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown ViewModel class $modelClass")
        return try {
            provider.get() as T
        } catch (e: Exception) {
            throw RuntimeException()
        }
    }
}