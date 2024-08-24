package com.jeff01478.cb.mhxxinfo.weapon.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jeff01478.cb.mhxxinfo.weapon.WeaponRepository
import com.jeff01478.cb.mhxxinfo.weapon.data.WeaponMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeaponMapViewModel(private val weaponType: String?,
                         application: Application) : AndroidViewModel(application) {
    private val repository = WeaponRepository(application)
    private val _weaponMap = MutableLiveData<List<WeaponMap>>()
    val weaponMap: LiveData<List<WeaponMap>> = _weaponMap

    fun loadWeaponMap() {
        viewModelScope.launch(Dispatchers.IO) {
            val weaponsList = repository.getWeaponMap(weaponType)
            _weaponMap.postValue(weaponsList)
        }
    }
}