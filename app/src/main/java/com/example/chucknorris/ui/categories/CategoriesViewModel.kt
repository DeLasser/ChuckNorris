package com.example.chucknorris.ui.categories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.chucknorris.Interactors.GetCategoriesInteractor
import com.example.chucknorris.ui.core.BaseViewModel
import org.koin.android.ext.android.inject

class CategoriesViewModel constructor(
    app: Application,
    private val handle: SavedStateHandle
) : BaseViewModel(app) {

    private val categoriesInteractor: GetCategoriesInteractor by getApplication<Application>().inject()

    val categoriesLiveData: MutableLiveData<List<String>> =
        handle.getLiveData("categories", ArrayList())
    val isUpdate: MutableLiveData<Boolean> = handle.getLiveData("isUpdate", false)

    init {
        if (categoriesLiveData.value.isNullOrEmpty()) {
            updateCategories()
        }
    }

    fun updateCategories() {
        handle.set("isUpdate", true)
        categoriesInteractor.invoke(this.viewModelScope, null, { categories ->
            handle.set("categories", categories)
            handle.set("isUpdate", false)
        }, {
            errorLiveData.value = it.message
            handle.set("isUpdate", false)
        })
    }


}