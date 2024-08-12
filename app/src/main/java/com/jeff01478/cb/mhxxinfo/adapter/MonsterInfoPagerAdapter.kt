package com.jeff01478.cb.mhxxinfo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jeff01478.cb.mhxxinfo.GlobalVariable
import com.jeff01478.cb.mhxxinfo.data.MonsterMaterial
import com.jeff01478.cb.mhxxinfo.fragment.MonsterMaterialFragment
import com.jeff01478.cb.mhxxinfo.fragment.MonsterWeaknessFragment

class MonsterInfoPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val context = fragmentActivity

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MonsterWeaknessFragment(context)
            1 -> MaterialFregment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }

    override fun getItemCount(): Int = 2

    private fun MaterialFregment(): Fragment =
        if (GlobalVariable.isDev) {
            MonsterMaterialFragment(context)
        } else {
            MonsterMaterialFragment(context)
        }
}