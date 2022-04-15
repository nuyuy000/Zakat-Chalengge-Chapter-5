package com.example.myapplicationn

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "zakat")
data class Zakat(

@PrimaryKey(autoGenerate = true)
var id :Int?,
@ColumnInfo(name ="jeniszakat")
var jenisZakat : String?,

@ColumnInfo(name = "deskripsi")
var deskripsi : String?,
)
