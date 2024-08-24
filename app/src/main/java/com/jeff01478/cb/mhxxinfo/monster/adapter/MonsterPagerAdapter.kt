package com.jeff01478.cb.mhxxinfo.monster.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jeff01478.cb.mhxxinfo.monster.fragment.LargeMonsterFragment
import com.jeff01478.cb.mhxxinfo.monster.fragment.SmailMonsterFragment

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