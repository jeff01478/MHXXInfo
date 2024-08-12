package com.jeff01478.cb.mhxxinfo.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jeff01478.cb.mhxxinfo.R
import com.jeff01478.cb.mhxxinfo.adapter.MonsterInfoPagerAdapter
import com.jeff01478.cb.mhxxinfo.data.Monster
import com.jeff01478.cb.mhxxinfo.viewModel.MonsterInfoViewModel
import org.w3c.dom.Text

class MonsterInfoActivity : AppCompatActivity() {

    private lateinit var monsterInfoViewModel: MonsterInfoViewModel
    private lateinit var monsterData: List<Monster>

    private lateinit var monsterName: TextView
    private lateinit var btnBack: ImageButton
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    private var index = 0
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monster_info)

        id = intent.getIntExtra("id", 0)

        initObject()
        monsterInfoViewModel = ViewModelProvider(this).get(MonsterInfoViewModel::class.java)
        observeViewModel()
        monsterInfoViewModel.loadMonsters()

        val adapter = MonsterInfoPagerAdapter(this)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "弱點"
                1 -> "素材"
                else -> "GOOD"
            }
        }.attach()

        tabLayout.setTabTextColors(
            Color.WHITE,
            Color.WHITE
        )

        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun initObject() {
        monsterName = findViewById(R.id.monsterName)
        btnBack = findViewById(R.id.btnBack)
        tabLayout = findViewById(R.id.monsterInfoTabLayout)
        viewPager = findViewById(R.id.monsterInfoViewPager)
    }

    private fun observeViewModel() {
        monsterInfoViewModel.monsters.observe(this) { monsters ->
            monsterData = monsters
            index = monsterData.indexOfFirst { it.id == id }
            monsterName.text = monsterData[index].name
        }
    }
}