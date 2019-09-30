package com.example.chucknorris.ui.core

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

open class BaseViewModel(app: Application) : AndroidViewModel(app) {

    val errorLiveData = MutableLiveData<String>()
}