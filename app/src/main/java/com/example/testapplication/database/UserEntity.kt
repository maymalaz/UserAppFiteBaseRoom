package com.example.testapplication.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UserEntity(
   @PrimaryKey(autoGenerate = false)
   @NonNull
   var id:Long?=null,
   var name:String?=null,
   var email:String?=null,
   var mobile:String?=null,
   var password:String?=null
)