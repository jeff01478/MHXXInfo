package com.jeff01478.cb.mhxxinfo.weapon

import android.content.Context
import com.jeff01478.cb.mhxxinfo.weapon.data.Weapon
import com.jeff01478.cb.mhxxinfo.weapon.data.WeaponMap
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class WeaponRepository(private val context: Context) {
    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    fun getWeapon(): List<Weapon> {
        val weaponList = ArrayList<Weapon>()
        val list = listOf("大劍", "單手劍", "雙劍", "太刀", "大槌", "狩獵笛", "長槍", "銃槍", "斬擊斧",
            "盾斧", "操蟲棍", "弓", "重弩", "輕弩")

        var type = 0
        list.forEach {
            weaponList.add(Weapon(it, "weapon_type_${type}", it))
            type++
        }
        return weaponList
    }

    fun getWeaponMap(): List<WeaponMap> {
        val jsonString = context.assets.open("greatSwordData.json").bufferedReader()
            .use { it.readText() }
        val type = Types.newParameterizedType(List::class.java, WeaponMap::class.java)
        val adapter = moshi.adapter<List<WeaponMap>>(type)
        return adapter.fromJson(jsonString) ?: emptyList()
    }
}