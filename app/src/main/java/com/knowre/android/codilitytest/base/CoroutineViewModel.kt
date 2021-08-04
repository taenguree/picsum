package com.knowre.android.codilitytest.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


internal open class CoroutineViewModel constructor(private vararg val models: BaseStateModel<*, *, *>) : ViewModel() {

    init { models.forEach { it.setScope(viewModelScope) } }

    open fun onCreate(savedInstanceState: Bundle?) {
        models
                .filterIsInstance<LifecycleAwareBaseStateModel<*,*,*>>()
                .forEach { it.onCreate(savedInstanceState) }
    }

    open fun onStart() {
        models
                .filterIsInstance<LifecycleAwareBaseStateModel<*,*,*>>()
                .forEach { it.onStart() }
    }

    open fun onResume() {
        models
                .filterIsInstance<LifecycleAwareBaseStateModel<*,*,*>>()
                .forEach { it.onResume() }
    }

    open fun onPause() {
        models
                .filterIsInstance<LifecycleAwareBaseStateModel<*,*,*>>()
                .forEach { it.onPause() }
    }

    open fun onStop() {
        models
                .filterIsInstance<LifecycleAwareBaseStateModel<*,*,*>>()
                .forEach { it.onStop() }
    }

    open fun onDestroy() {
        models
                .filterIsInstance<LifecycleAwareBaseStateModel<*,*,*>>()
                .forEach { it.onDestroy() }
    }

    override fun onCleared() {
        super.onCleared()

        models
            .filterIsInstance<LifecycleAwareBaseStateModel<*,*,*>>()
            .forEach { it.onCleared() }
    }

}