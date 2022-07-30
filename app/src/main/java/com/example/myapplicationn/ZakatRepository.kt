package com.example.myapplicationn

import android.content.Context
import kotlinx.coroutines.coroutineScope

class ZakatRepository(private val context: Context) {

    private var myDatabase = ZakatDatabase.getInstance(context)

    suspend fun getAllZakat(): List<Zakat>? = coroutineScope {
        return@coroutineScope myDatabase?.zakatDao()?.getAllZakat()
    }

    suspend fun addZakat(zakat: Zakat):Long? = coroutineScope {
        return@coroutineScope myDatabase?.zakatDao()?.addZakat(zakat)
    }

    suspend fun updateZakat(zakat: Zakat):Int? = coroutineScope {
        return@coroutineScope myDatabase?.zakatDao()?.updateZakat(zakat)
    }

    suspend fun deleteZakat(zakat: Zakat):Int? = coroutineScope {
        return@coroutineScope myDatabase?.zakatDao()?.deleteZakat(zakat)
    }

}