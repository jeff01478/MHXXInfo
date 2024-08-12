package com.jeff01478.cb.mhxxinfo.data

data class Monster(
    val id: Int,
    val name: String,
    val imageResource: String,
    val weakness: Map<String, Map<String, String>>,
    val isDev: Boolean = false
)