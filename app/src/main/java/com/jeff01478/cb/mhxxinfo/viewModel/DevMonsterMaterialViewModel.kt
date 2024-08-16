package com.jeff01478.cb.mhxxinfo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jeff01478.cb.mhxxinfo.data.DevMonsterMaterial
import com.jeff01478.cb.mhxxinfo.data.MonsterMaterial
import com.jeff01478.cb.mhxxinfo.repository.MonsterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DevMonsterMaterialViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MonsterRepository(application)
    private val _monsters = MutableLiveData<List<DevMonsterMaterial>>()
    val monsters: LiveData<List<DevMonsterMaterial>> = _monsters

    fun loadMonstersMaterial() {
        viewModelScope.launch(Dispatchers.IO) {
            val monsterMaterialList = repository.getDevMonsterMaterial()
            _monsters.postValue(monsterMaterialList)
        }
    }
}