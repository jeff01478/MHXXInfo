package com.jeff01478.cb.mhxxinfo.monster.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jeff01478.cb.mhxxinfo.monster.data.Monster
import com.jeff01478.cb.mhxxinfo.monster.MonsterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MonsterInfoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MonsterRepository(application)
    private val _monsters = MutableLiveData<List<Monster>>()
    val monsters: LiveData<List<Monster>> = _monsters

    fun loadMonsters() {
        viewModelScope.launch(Dispatchers.IO) {
            val monsterList = repository.getMonsters()
            _monsters.postValue(monsterList)
        }
    }

    fun loadSmailMonsters() {
        viewModelScope.launch(Dispatchers.IO) {
            val monsterList = repository.getSmailMonsters()
            _monsters.postValue(monsterList)
        }
    }
}