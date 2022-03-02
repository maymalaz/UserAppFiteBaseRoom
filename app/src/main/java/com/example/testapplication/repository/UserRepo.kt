package com.example.testapplication.repository

import com.example.testapplication.database.UserDao
import com.example.testapplication.database.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepo  constructor(private val userDao: UserDao) {

    //insert user details to room
    suspend fun createUserRecords(user: UserEntity) : Long {
        return userDao.insertToRoomDatabase(user)
    }

    //get single user details e.g with id 1
    val getUserDetails: Flow<List<UserEntity>> get() =  userDao.getUserDetails()

    //delete single user record
    suspend fun deleteSingleUserRecord() {
        userDao.deleteSingleUserDetails(1)
    }

}