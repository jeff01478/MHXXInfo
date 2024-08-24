package com.jeff01478.cb.mhxxinfo.monster.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jeff01478.cb.mhxxinfo.monster.data.MonsterMaterial
import com.jeff01478.cb.mhxxinfo.monster.MonsterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MonsterMaterialViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MonsterRepository(application)
    private val _monsters = MutableLiveData<List<MonsterMaterial>>()
    val monsters: LiveData<List<MonsterMaterial>> = _monsters

    fun loadMonstersMaterial() {
        viewModelScope.launch(Dispatchers.IO) {
            val monsterMaterialList = repository.getMonsterMaterial()
            _monsters.postValue(monsterMaterialList)
        }
    }

    fun loadSmailMonstersMaterial() {
        viewModelScope.launch(Dispatchers.IO) {
            val monsterMaterialList = repository.getSmailMonsterMaterial()
            _monsters.postValue(monsterMaterialList)
        }
    }
}