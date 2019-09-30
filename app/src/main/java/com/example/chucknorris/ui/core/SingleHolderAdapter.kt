package com.example.chucknorris.ui.core

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class SingleHolderAdapter<T> private constructor(
    private var items: List<T>,
    private var holderCreator: (parent: ViewGroup) -> BaseViewHolder<T>,
    private var onItemClick: ((item: T) -> Unit)? = null
) : RecyclerView.Adapter<SingleHolderAdapter.BaseViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return holderCreator.invoke(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        items[position].let { item ->
            holder.bindView(item)
            holder.itemView.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }

    }

    fun updateItems(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindView(item: T)
    }

    class Builder<T> {
        private var onItemClick: ((item: T) -> Unit)? = null
        private lateinit var items: List<T>
        private lateinit var holderCreator: (parent: ViewGroup) -> BaseViewHolder<T>

        fun onItemClick(onItemClick: ((item: T) -> Unit)?): Builder<T> {
            this.onItemClick = onItemClick
            return this
        }

        fun items(items: List<T>): Builder<T> {
            this.items = items
            return this
        }

        fun holderCreator(holderCreator: (parent: ViewGroup) -> BaseViewHolder<T>): Builder<T> {
            this.holderCreator = holderCreator
            return this
        }

        fun build(): SingleHolderAdapter<T> {
            return SingleHolderAdapter(items, holderCreator, onItemClick)
        }

    }
}