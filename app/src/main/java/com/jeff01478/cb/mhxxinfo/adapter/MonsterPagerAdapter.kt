package com.jeff01478.cb.mhxxinfo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jeff01478.cb.mhxxinfo.GlobalVariable
import com.jeff01478.cb.mhxxinfo.fragment.DevMonsterMaterialFragment
import com.jeff01478.cb.mhxxinfo.fragment.LargeMonsterFragment
import com.jeff01478.cb.mhxxinfo.fragment.MonsterMaterialFragment
import com.jeff01478.cb.mhxxinfo.fragment.MonsterWeaknessFragment
import com.jeff01478.cb.mhxxinfo.fragment.SmailMonsterFragment

class MonsterPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val context = fragment

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> LargeMonsterFragment()
            1 -> SmailMonsterFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }

    override fun getItemCount(): Int = 2
}