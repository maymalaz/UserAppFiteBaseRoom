package com.example.testapplication.preference

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {
    //Get saved Key
    // check if the preference has been saved or not
    fun savedKey() : Flow<Boolean>

    //Set a value to the preference
    suspend fun setSavedKey(order: Boolean)
}