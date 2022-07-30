package com.example.myapplicationn

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["username"], unique = true)])
data class Akun(
    @PrimaryKey(autoGenerate = true)
    var id :Int?,
    var username : String?,
    var password : String?,
)
