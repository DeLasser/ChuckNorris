package com.example.chucknorris.ui.fact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.chucknorris.R
import com.example.chucknorris.ui.core.BaseFragment
import kotlinx.android.synthetic.main.fragment_fact.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class FactFragment : BaseFragment() {
    private val factViewModel: FactViewModel by inject { parametersOf(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fact, container, false)
        initData(view)
        initData(savedInstanceState)
        baseSubscribe(factViewModel)
        return view
    }

    private fun initData(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            arguments?.getString("category")?.let {
                factViewModel.loadFact(it)
            }
        }
    }

    private fun initData(view: View) {
        factViewModel.factLiveData.observe(this, Observer {
            view.fact_text_view.text = it.value
            view.image_view.setImageURI(it.imageUrl)
        })
    }

}