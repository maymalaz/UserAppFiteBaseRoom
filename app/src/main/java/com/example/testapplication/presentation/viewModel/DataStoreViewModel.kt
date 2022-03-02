package com.example.testapplication.presentation.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.testapplication.preference.PreferenceStorage
import kotlinx.coroutines.launch

//data is stored asynchronously, consistently, and translationally.
class DataStoreViewModel @ViewModelInject constructor(private val preferenceStorage: PreferenceStorage): ViewModel() {

    //saved key as liveData
    val savedKey = preferenceStorage.savedKey().asLiveData()
    fun setSavedKey(key: Boolean) {
        viewModelScope.launch {
            preferenceStorage.setSavedKey(key)
        }
    }

}