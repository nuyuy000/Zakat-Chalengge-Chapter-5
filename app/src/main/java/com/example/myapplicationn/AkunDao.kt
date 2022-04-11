package com.example.myapplicationn

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AkunDao {
    @Query("SELECT * FROM akun WHERE  username = :username AND password = :password")
    fun login(username: String, password: String):Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(akun: Akun): Long
}