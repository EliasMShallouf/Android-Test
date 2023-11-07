package com.elias_shallouf.example.androidtest.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elias_shallouf.example.androidtest.models.ListItem

class SimpleViewModel(
    private var data: List<ListItem>,
    var searchText: String
): ViewModel() {
    private var _liveData: MutableLiveData<List<ListItem>> = MutableLiveData()
    val liveData = _liveData

    init {
        _liveData.value = data
    }

    fun search(txt: String) {
        searchText = txt
        _liveData.value = data.filter { it.title.contains(txt) }
    }
}