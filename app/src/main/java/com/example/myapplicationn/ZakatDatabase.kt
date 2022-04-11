package com.example.myapplicationn

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Akun::class], version = 1)
abstract class ZakatDatabase : RoomDatabase() {

    abstract fun akunDao() : AkunDao

    companion object {

        @Volatile
        private var INSTANCE : ZakatDatabase? = null
        fun getInstance (context : Context) : ZakatDatabase ? {
            if (INSTANCE==null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    ZakatDatabase::class.java,"zakat.db").build()

            }
            return INSTANCE

        }
        fun destroyInstance ( ) {
            INSTANCE = null
        }
    }
}