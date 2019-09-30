package com.example.chucknorris.ui.categories

import android.view.View
import com.example.chucknorris.ui.core.SingleHolderAdapter
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryViewHolder(itemView: View) : SingleHolderAdapter.BaseViewHolder<String>(itemView) {

    override fun bindView(item: String) {
        this.itemView.text.text = item
    }

}