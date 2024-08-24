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
import com.jeff01478.cb.mhxxinfo.weapon.factory.WeaponMapViewModelFactory
import com.jeff01478.cb.mhxxinfo.weapon.adapter.WeaponMapNoDerivativeAdapter
import com.jeff01478.cb.mhxxinfo.weapon.viewModel.WeaponMapViewModel

class WeaponMapNoDerivativeFragment(private val weaponType: String?,
                                    private val savePosition: Int? = null) : Fragment() {

    private lateinit var weaponMapRecyclerView: RecyclerView
    private lateinit var weaponMapViewModel: WeaponMapViewModel
    private lateinit var weaponMapViewModelFactory: WeaponMapViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(requireContext()).inflate(R.layout.fragment_weapon_map_no_derivative,
            container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObject(view)
        setRecyclerView()

        weaponMapViewModelFactory = WeaponMapViewModelFactory(weaponType, requireActivity().application)
        weaponMapViewModel = ViewModelProvider(this, weaponMapViewModelFactory).
        get(WeaponMapViewModel::class.java)
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
            val adapter = WeaponMapNoDerivativeAdapter(weapons)
            weaponMapRecyclerView.adapter = adapter
        }
    }

    fun savePosition(): Int {
        val layoutManager = weaponMapRecyclerView.layoutManager as LinearLayoutManager
        return layoutManager.findFirstVisibleItemPosition()
    }
}