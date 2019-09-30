package com.example.chucknorris.ui.fact

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.chucknorris.Interactors.GetFactInteractor
import com.example.chucknorris.entities.Fact
import com.example.chucknorris.ui.core.BaseViewModel
import org.koin.android.ext.android.inject

class FactViewModel(
    app: Application,
    private val handle: SavedStateHandle
) : BaseViewModel(app) {

    private val getFactInteractor: GetFactInteractor by app.inject()
    val factLiveData: MutableLiveData<Fact>  = handle.getLiveData("fact")

    fun loadFact(category: String) {
        getFactInteractor.invoke(
            viewModelScope,
            category,
            onResult = {
                handle.set("fact", it)
            }, onError = {
                errorLiveData.value = it.message
            })
    }
}