package com.tam.mybike.ui.screen.bikes

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BikesViewModel @Inject constructor() : ViewModel() {

    private val mutableState = MutableStateFlow(BikesState())
    val state = mutableState.asStateFlow()

}