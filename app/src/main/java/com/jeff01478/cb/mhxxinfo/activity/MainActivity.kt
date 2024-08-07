package com.jeff01478.cb.mhxxinfo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeff01478.cb.mhxxinfo.DashedDividerItemDecoration
import com.jeff01478.cb.mhxxinfo.R
import com.jeff01478.cb.mhxxinfo.adapter.MonsterAdapter
import com.jeff01478.cb.mhxxinfo.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var monsterRecyclerView: RecyclerView
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MonsterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObject()

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setupRecyclerView()
        observeViewModel()

        mainViewModel.loadMonsters()
    }

    private fun initObject() {
        monsterRecyclerView = findViewById(R.id.monsterRecyclerview)
    }

    private fun setupRecyclerView() {
        monsterRecyclerView.layoutManager = LinearLayoutManager(this)
        monsterRecyclerView.addItemDecoration(DashedDividerItemDecoration(this))
    }

    private fun observeViewModel() {
        mainViewModel.monsters.observe(this) { monsters ->
            adapter = MonsterAdapter(monsters)
            monsterRecyclerView.adapter = adapter
        }
    }
}