package com.jeff01478.cb.mhxxinfo.weapon.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jeff01478.cb.mhxxinfo.weapon.WeaponRepository
import com.jeff01478.cb.mhxxinfo.weapon.data.Weapon
import com.jeff01478.cb.mhxxinfo.weapon.data.WeaponMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeaponViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = WeaponRepository(application)
    private val _weapons = MutableLiveData<List<Weapon>>()
    val weapons: LiveData<List<Weapon>> = _weapons

    fun loadWeapon() {
        viewModelScope.launch(Dispatchers.IO) {
            val weaponsList = repository.getWeapon()
            _weapons.postValue(weaponsList)
        }
    }
}