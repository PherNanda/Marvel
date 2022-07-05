package com.fernandagl.marvel.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernandagl.marvel.model.comics.Comic
import com.fernandagl.marvel.data.repository.GetComicsByCharacterIdUseCase
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val getComicsByCharacterIdUseCase: GetComicsByCharacterIdUseCase
): ViewModel() {

    private val _comicsLiveData = MutableLiveData<List<Comic>>()
    val comics = _comicsLiveData as LiveData<List<Comic>>

    fun getComicsByCharacterId(characterId: Int) {
        viewModelScope.launch {
            val comics = getComicsByCharacterIdUseCase(characterId)
            _comicsLiveData.value = comics
        }
    }
}