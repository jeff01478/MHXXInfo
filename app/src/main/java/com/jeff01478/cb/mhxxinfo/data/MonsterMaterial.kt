package com.jeff01478.cb.mhxxinfo.data

data class MonsterMaterial(
    val id: Int,
    val name: String,
    val material: Map<String, Map<String, List<String>>>
)