package com.example.recyclerviewsearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerviewsearch.model.data.Color
import kotlinx.coroutines.runBlocking

class MainViewModel(private val repository:MainRepository):ViewModel() {

    private val _colorsList = MutableLiveData<List<Color>>()
    private var colors = listOf<Color>()
    val colorsList:LiveData<List<Color>> get() = _colorsList

    fun getColors(){
        runBlocking {
            colors = repository.prepareData()
            Log.d("fetch", "data ${colors.size}")
            _colorsList.value = colors
        }
    }
    fun searchColors(searchString:String){
       val filteredList = colors.filter {
           it.name.startsWith(searchString, true)
       }
        _colorsList.postValue(filteredList)
    }
    fun showAllColors(){
        _colorsList.postValue(colors)
    }

}

class MainViewModelFactory(val repository:MainRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}