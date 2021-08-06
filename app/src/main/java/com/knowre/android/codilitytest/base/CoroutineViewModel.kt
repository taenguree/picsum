package com.knowre.android.codilitytest.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


internal open class CoroutineViewModel constructor(private vararg val models: BaseStateModel<*, *, *>) : ViewModel() {

    init { models.forEach { it.setScope(viewModelScope) } }

    open fun onCreate(savedInstanceState: Bundle?, intent: Intent? = null) {
        models
                .filterIsInstance<LifecycleAwareStateModel<*,*,*>>()
                .forEach { it.onCreate(savedInstanceState, intent) }
    }

    open fun onStart() {
        models
                .filterIsInstance<LifecycleAwareStateModel<*,*,*>>()
                .forEach { it.onStart() }
    }

    open fun onResume() {
        models
                .filterIsInstance<LifecycleAwareStateModel<*,*,*>>()
                .forEach { it.onResume() }
    }

    open fun onPause() {
        models
                .filterIsInstance<LifecycleAwareStateModel<*,*,*>>()
                .forEach { it.onPause() }
    }

    open fun onStop() {
        models
                .filterIsInstance<LifecycleAwareStateModel<*,*,*>>()
                .forEach { it.onStop() }
    }

    open fun onDestroy() {
        models
                .filterIsInstance<LifecycleAwareStateModel<*,*,*>>()
                .forEach { it.onDestroy() }
    }

    override fun onCleared() {
        super.onCleared()

        models
            .filterIsInstance<LifecycleAwareStateModel<*,*,*>>()
            .forEach { it.onCleared() }
    }

}