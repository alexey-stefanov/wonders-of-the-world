package com.alexstephanov.wondersoftheworld.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.listeners.OnItemClickListener

abstract class GenericAdapter<T>(
    private var itemList: List<T>,
    private val itemClickListener: OnItemClickListener<T>?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        , viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(itemList[position], itemClickListener)
    }

    override fun getItemCount(): Int = itemList.size

    fun update(items: List<T>) {
        itemList = items
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = getLayoutId(position,
    itemList[position])

    protected abstract fun getLayoutId(position: Int, obj: T): Int

    private fun getViewHolder(view: View, viewType: Int) : RecyclerView.ViewHolder {
        return ViewHolderFactory.create(view, viewType)
    }

    internal interface Binder<T> {
        fun bind(item: T, listener: OnItemClickListener<T>?)
    }
}