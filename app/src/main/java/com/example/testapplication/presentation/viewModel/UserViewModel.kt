package com.example.testapplication.presentation.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.database.UserEntity
import com.example.testapplication.repository.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserViewModel  @ViewModelInject constructor(private val userRepo: UserRepo): ViewModel() {

    /**
     * Insert user details
     */
    private val _response = MutableLiveData<Long>()
    val response: LiveData<Long> = _response

    //insert user details to room database
    fun insertUserDetails(user: UserEntity){
        viewModelScope.launch(Dispatchers.IO) {
            _response.postValue(userRepo.createUserRecords(user))
        }
    }

    /**
     * Retrieve user details
     */

    private val _userDetails = MutableStateFlow<List<UserEntity>>(emptyList())
    val userDetails : StateFlow<List<UserEntity>> =  _userDetails

    fun doGetUserDetails(){
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.getUserDetails
                .catch { e->
                    //Log error here
                }
                .collect {
                    _userDetails.value = it
                }
        }
    }

    /**
     * Delete single user record
     */
    fun doDeleteSingleUserRecord(){
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.deleteSingleUserRecord()
        }
    }

}