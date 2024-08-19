package com.jeff01478.cb.mhxxinfo.repository

import android.content.Context
import com.jeff01478.cb.mhxxinfo.data.DevMonsterMaterial
import com.jeff01478.cb.mhxxinfo.data.Monster
import com.jeff01478.cb.mhxxinfo.data.MonsterMaterial
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

    fun getSmailMonsters(): List<Monster> {
        val jsonString = context.assets.open("smailMonsterData.json").bufferedReader()
            .use { it.readText() }
        val type = Types.newParameterizedType(List::class.java, Monster::class.java)
        val adapter = moshi.adapter<List<Monster>>(type)
        return adapter.fromJson(jsonString) ?: emptyList()
    }

    fun getMonsterMaterial(): List<MonsterMaterial> {
        val jsonString = context.assets.open("MonsterMaterial.json").bufferedReader()
            .use { it.readText() }
        val type = Types.newParameterizedType(List::class.java, MonsterMaterial::class.java)
        val adapter = moshi.adapter<List<MonsterMaterial>>(type)
        return adapter.fromJson(jsonString) ?: emptyList()
    }

    fun getDevMonsterMaterial(): List<DevMonsterMaterial> {
        val jsonString = context.assets.open("devMonsterMaterial.json").bufferedReader()
            .use { it.readText() }
        val type = Types.newParameterizedType(List::class.java, DevMonsterMaterial::class.java)
        val adapter = moshi.adapter<List<DevMonsterMaterial>>(type)
        return adapter.fromJson(jsonString) ?: emptyList()
    }

    fun getSmailMonsterMaterial(): List<MonsterMaterial> {
        val jsonString = context.assets.open("smailMonsterMaterial.json").bufferedReader()
            .use { it.readText() }
        val type = Types.newParameterizedType(List::class.java, MonsterMaterial::class.java)
        val adapter = moshi.adapter<List<MonsterMaterial>>(type)
        return adapter.fromJson(jsonString) ?: emptyList()
    }
}