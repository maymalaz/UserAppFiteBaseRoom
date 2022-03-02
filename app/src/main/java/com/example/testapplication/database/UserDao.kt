package com.example.testapplication.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    /**
     * CREATE
     */
    //insert data to room database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToRoomDatabase(user: UserEntity) : Long

    /**
     * READ
     */
    //get all users inserted to room database...normally this is supposed to be a list of users
    @Transaction
    @Query("SELECT * FROM users_table ORDER BY id DESC")
    fun getUserDetails() : Flow<List<UserEntity>>

    //get single user inserted to room database
    @Transaction
    @Query("SELECT * FROM users_table WHERE id = :id ORDER BY id DESC")
    fun getSingleUserDetails(id: Long) : Flow<UserEntity>

    /**
     * UPDATE
     */
    //update user details
    @Update
    suspend fun updateUserDetails(user: UserEntity)

    /**
     * DELETE
     */
    //delete single user details
    @Query("DELETE FROM users_table WHERE id = :id")
    suspend fun deleteSingleUserDetails(id: Int)

    //delete all user details
    @Delete
    suspend fun deleteAllUsersDetails(user: UserEntity)
}