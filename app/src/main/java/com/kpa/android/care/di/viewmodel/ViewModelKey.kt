package com.kpa.android.care.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 *    author : kpa
 *    e-mail : billkp@yeah.net
 */
@MapKey
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)