package com.example.myapplicationn

import android.content.Context
import kotlinx.coroutines.coroutineScope

class AkunRepository(private val context: Context) {

    private var myDatabase = ZakatDatabase.getInstance(context)

    suspend fun login(email: String, password: String): Akun? = coroutineScope {
        return@coroutineScope myDatabase?.akunDao()?.login(email, password)
    }

    suspend fun register(user: Akun): Long? = coroutineScope {
        return@coroutineScope myDatabase?.akunDao()?.insertAkun(user)
    }


    suspend fun checkEmailIfExist(email: String):Akun? = coroutineScope {
        return@coroutineScope myDatabase?.akunDao()?.checkEmailExist(email)
    }


}