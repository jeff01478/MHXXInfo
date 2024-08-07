package com.jeff01478.cb.mhxxinfo.repository

import android.content.Context
import com.jeff01478.cb.mhxxinfo.data.Monster
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MonsterRepository(private val context: Context) {
    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    fun getMonsters(): List<Monster> {
        val jsonString = context.assets.open("MonsterData.json").bufferedReader()
            .use { it.readText() }
        val type = Types.newParameterizedType(List::class.java, Monster::class.java)
        val adapter = moshi.adapter<List<Monster>>(type)
        return adapter.fromJson(jsonString) ?: emptyList()
    }
}