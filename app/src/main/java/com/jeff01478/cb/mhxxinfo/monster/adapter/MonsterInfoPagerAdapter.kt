package com.jeff01478.cb.mhxxinfo.monster.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jeff01478.cb.mhxxinfo.GlobalVariable
import com.jeff01478.cb.mhxxinfo.monster.fragment.DevMonsterMaterialFragment
import com.jeff01478.cb.mhxxinfo.monster.fragment.MonsterMaterialFragment
import com.jeff01478.cb.mhxxinfo.monster.fragment.MonsterWeaknessFragment

class MonsterInfoPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val context = fragmentActivity

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MonsterWeaknessFragment(context)
            1 -> materialFragment(position)
            2 -> materialFragment(position)
            3 -> materialFragment(position)
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }

    override fun getItemCount(): Int = 4

    private fun materialFragment(position: Int): Fragment =
        if (GlobalVariable.isDev)
            DevMonsterMaterialFragment(context, position)
        else
            MonsterMaterialFragment(context, position)
}