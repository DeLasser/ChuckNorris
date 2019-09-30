package com.example.chucknorris.ui.core

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

open class BaseFragment: Fragment() {

    fun baseSubscribe(viewModel: BaseViewModel) {
        viewModel.errorLiveData.observe(this, Observer{
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }
}