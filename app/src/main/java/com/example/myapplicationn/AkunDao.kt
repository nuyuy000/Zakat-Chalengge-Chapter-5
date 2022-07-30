package com.example.myapplicationn

import androidx.room.*

@Dao
interface AkunDao {

    @Query("SELECT * FROM Akun WHERE username = :email AND password = :password")
    fun login(email: String, password: String):Akun?

    @Query("SELECT * FROM Akun WHERE username = :email")
    fun checkEmailExist(email: String):Akun?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAkun(user: Akun):Long

}