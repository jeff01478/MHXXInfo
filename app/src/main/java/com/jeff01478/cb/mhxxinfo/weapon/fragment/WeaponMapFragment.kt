package com.jeff01478.cb.mhxxinfo.weapon.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeff01478.cb.mhxxinfo.DashedDividerItemDecoration
import com.jeff01478.cb.mhxxinfo.R
import com.jeff01478.cb.mhxxinfo.weapon.adapter.WeaponMapAdapter
import com.jeff01478.cb.mhxxinfo.weapon.viewModel.WeaponMapViewModel
import kotlin.reflect.jvm.internal.impl.incremental.components.Position

class WeaponMapFragment(private val savePosition: Int? = null) : Fragment() {

    private lateinit var weaponMapRecyclerView: RecyclerView
    private lateinit var weaponMapViewModel: WeaponMapViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_weapon_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObject(view)
        setRecyclerView()
        weaponMapViewModel = ViewModelProvider(this).get(WeaponMapViewModel::class.java)
        weaponMapViewModel.loadWeaponMap()
        observeViewModel()
    }

    private fun initObject(view: View) {
        weaponMapRecyclerView = view.findViewById(R.id.weaponMapRecyclerview)
    }

    private fun setRecyclerView() {
        weaponMapRecyclerView.layoutManager = LinearLayoutManager(context)
        weaponMapRecyclerView.addItemDecoration(DashedDividerItemDecoration(requireContext()))
        if (savePosition != null) {
            weaponMapRecyclerView.scrollToPosition(savePosition)
        }
    }

    private fun observeViewModel() {
        weaponMapViewModel.weaponMap.observe(this) { weapons ->
            val adapter = WeaponMapAdapter(weapons)
            weaponMapRecyclerView.adapter = adapter
        }
    }

    fun savePosition(): Int {
        val layoutManager = weaponMapRecyclerView.layoutManager as LinearLayoutManager
        return layoutManager.findFirstVisibleItemPosition()
    }
}