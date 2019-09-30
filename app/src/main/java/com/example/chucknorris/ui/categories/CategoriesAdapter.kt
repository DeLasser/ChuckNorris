package com.example.chucknorris.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorris.R

class CategoriesAdapter(private var categories: List<String>) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    private var onItemClick: ((item: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_category,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        categories[position].let { item ->
            holder.bindView(item)
            holder.itemView.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }

    }

}