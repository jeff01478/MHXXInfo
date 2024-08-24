package com.jeff01478.cb.mhxxinfo.monster.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jeff01478.cb.mhxxinfo.R
import com.jeff01478.cb.mhxxinfo.monster.adapter.MonsterInfoPagerAdapter
import com.jeff01478.cb.mhxxinfo.monster.data.Monster
import com.jeff01478.cb.mhxxinfo.monster.viewModel.MonsterInfoViewModel

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
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        id = intent.getIntExtra("id", 0)
        val monster = intent.getBooleanExtra("largeMonster", true)

        initObject()
        monsterInfoViewModel = ViewModelProvider(this).get(MonsterInfoViewModel::class.java)
        observeViewModel()

        if (monster)
            monsterInfoViewModel.loadMonsters()
        else
            monsterInfoViewModel.loadSmailMonsters()

        val adapter = MonsterInfoPagerAdapter(this)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "弱點"
                1 -> "G級"
                2 -> "上位"
                3 -> "下位"
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