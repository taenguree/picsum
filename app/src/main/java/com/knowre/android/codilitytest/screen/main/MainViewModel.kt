package com.knowre.android.codilitytest.screen.main

import com.knowre.android.codilitytest.base.CoroutineViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
internal class MainViewModel @Inject constructor(
        private val mainStateModel: MainStateModel

) : CoroutineViewModel() {

}