package com.alexstephanov.wondersoftheworld.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.listeners.OnItemClickListener
import com.alexstephanov.wondersoftheworld.model.ListItem

class ListAdapter(private val items: ArrayList<ListItem>, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ListAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.title_item)
        private val description = itemView.findViewById<TextView>(R.id.description_item)
        fun bind(item: ListItem) {
            title.text = item.title
            description.text = item.description
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION)
                    onItemClickListener.onItemClick(items[adapterPosition])
            }
        }
    }
}