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
import com.jeff01478.cb.mhxxinfo.weapon.adapter.WeaponAdapter
import com.jeff01478.cb.mhxxinfo.weapon.viewModel.WeaponViewModel

class WeaponsFragment : Fragment() {

    private lateinit var weaponRecyclerview: RecyclerView
    private lateinit var weaponViewModel: WeaponViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weapons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObject(view)
        setupRecyclerView()
        weaponViewModel = ViewModelProvider(this).get(WeaponViewModel::class.java)
        observeViewModel()
        weaponViewModel.loadWeapon()
    }

    private fun initObject(view: View) {
        weaponRecyclerview = view.findViewById(R.id.weaponRecyclerview)
    }

    private fun setupRecyclerView() {
        weaponRecyclerview.layoutManager = LinearLayoutManager(context)
        weaponRecyclerview.addItemDecoration(DashedDividerItemDecoration(requireContext()))
    }

    private fun observeViewModel() {
        weaponViewModel.weapons.observe(this) { weapons ->
            val adapter = WeaponAdapter(weapons)
            weaponRecyclerview.adapter = adapter
        }
    }
}