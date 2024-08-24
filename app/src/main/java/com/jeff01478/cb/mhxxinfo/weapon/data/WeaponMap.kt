package com.jeff01478.cb.mhxxinfo.weapon.data

data class WeaponMap(
    val id: Int,
    val type: String,
    val derivative: String,
    val name: String,
    val attack: String,
    val affinity: String = "0%",
    val elemental: String = "",
    val defense: String = "",
    val special: String = "",
    val sharpness: Map<String, List<String>>,
    val holo: String
)