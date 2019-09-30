package com.example.chucknorris.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.chucknorris.R
import com.example.chucknorris.ui.core.BaseFragment
import com.example.chucknorris.ui.core.SingleHolderAdapter
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_categories.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CategoriesFragment : BaseFragment() {

    val categoriesViewModel: CategoriesViewModel by inject { parametersOf(this) }

    private lateinit var adapter: SingleHolderAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = true
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        initView(view)
        bindData()
        baseSubscribe(categoriesViewModel)
        return view
    }

    private fun bindData() {
        categoriesViewModel.categoriesLiveData.observe(this, Observer {
            adapter.updateItems(it)
        })
        categoriesViewModel.isUpdate.observe(this, Observer {
            if (swiperefresh.isRefreshing != it) {
                swiperefresh.isRefreshing = it
            }
        })
    }

    private fun initView(view: View) {
        adapter = SingleHolderAdapter.Builder<String>()
            .holderCreator {
                CategoryViewHolder(
                    LayoutInflater.from(it.context).inflate(
                        R.layout.item_category,
                        it,
                        false
                    )
                )
            }
            .items(emptyList())
            .onItemClick {
                onCategoryClick(it)
            }
            .build()
        view.categories.adapter = adapter

        view.swiperefresh.setOnRefreshListener {
            categoriesViewModel.updateCategories()
        }
    }

    private fun onCategoryClick(category: String) {
        findNavController().navigate(
            R.id.action_categoriesFragment_to_factFragment,
            Bundle().apply {
                this.putString("category", category)
            })
    }

}