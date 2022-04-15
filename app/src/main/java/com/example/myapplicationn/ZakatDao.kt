package com.example.myapplicationn

import androidx.room.*

@Dao
interface ZakatDao {
    @Query ("SELECT*FROM zakat")
    fun getAllZakat (): List<Zakat>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addZakat(zakat: Zakat): Long

    @Update
    fun updateZakat(zakat: Zakat): Int

    @Delete
    fun deleteZakat(zakat: Zakat): Int
}