package com.jeff01478.cb.mhxxinfo.monster.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeff01478.cb.mhxxinfo.DashedDividerItemDecoration
import com.jeff01478.cb.mhxxinfo.R
import com.jeff01478.cb.mhxxinfo.monster.adapter.MonsterAdapter
import com.jeff01478.cb.mhxxinfo.monster.viewModel.MainViewModel

class LargeMonsterFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MonsterAdapter
    private lateinit var monsterRecyclerView: RecyclerView
    private lateinit var view: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_large_monster, container, false)
        initObject()
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setupRecyclerView()
        observeViewModel()

        mainViewModel.loadMonsters()
        return view
    }

    private fun setupRecyclerView() {
        monsterRecyclerView.layoutManager = LinearLayoutManager(context)
        monsterRecyclerView.addItemDecoration(DashedDividerItemDecoration(requireContext()))
    }

    private fun observeViewModel() {
        mainViewModel.monsters.observe(this) { monsters ->
            Log.d("GOOD", monsters[0].weakness.toString())
            adapter = MonsterAdapter(monsters)
            monsterRecyclerView.adapter = adapter
        }
    }

    private fun initObject() {
        monsterRecyclerView = view.findViewById(R.id.monsterRecyclerview)
    }
}