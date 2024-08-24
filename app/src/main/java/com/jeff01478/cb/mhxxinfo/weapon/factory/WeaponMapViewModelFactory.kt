package com.jeff01478.cb.mhxxinfo.weapon.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jeff01478.cb.mhxxinfo.weapon.viewModel.WeaponMapViewModel

class WeaponMapViewModelFactory(private val weaponType: String?,
                                private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeaponMapViewModel::class.java)) {
            return WeaponMapViewModel(weaponType, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}