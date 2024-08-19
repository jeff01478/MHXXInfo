package com.jeff01478.cb.mhxxinfo.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jeff01478.cb.mhxxinfo.R
import com.jeff01478.cb.mhxxinfo.adapter.MonsterInfoPagerAdapter
import com.jeff01478.cb.mhxxinfo.adapter.MonsterPagerAdapter

class MonsterFragment : Fragment() {
    private lateinit var view: View
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_monster, container, false)
        initObject()
        val adapter = MonsterPagerAdapter(this)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "大型魔物"
                1 -> "小型魔物"
                else -> "GOOD"
            }
        }.attach()

        tabLayout.setTabTextColors(
            Color.WHITE,
            Color.WHITE
        )
        return view
    }

    private fun initObject() {
        tabLayout = view.findViewById(R.id.monsterTabLayout)
        viewPager = view.findViewById(R.id.monsterViewPager)
    }
}