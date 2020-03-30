package com.alexstephanov.wondersoftheworld.list

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.listeners.OnItemClickListener
import com.alexstephanov.wondersoftheworld.model.AncientWondersListItemModel
import com.alexstephanov.wondersoftheworld.model.ModernWondersListItemModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.ancient_wonders_list_item.view.*
import kotlinx.android.synthetic.main.modern_wonders_list_item.view.*
import java.lang.Exception

object ViewHolderFactory {
    fun create(view: View, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.ancient_wonders_list_item -> AncientWondersViewHolder(view)
            R.layout.modern_wonders_list_item -> ModernWondersViewHolder(view)
            else -> throw Exception("Wrong view type")
        }
    }

    class AncientWondersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            GenericAdapter.Binder<AncientWondersListItemModel> {
        override fun bind(
            item: AncientWondersListItemModel,
            listener: OnItemClickListener<AncientWondersListItemModel>?
        ) {
            itemView.apply {
                ancient_wonders_item_title.text = item.title
                Picasso.get().load(item.url).into(ancient_wonders_item_thumbnail)
                setOnClickListener {
                    listener?.onItemClick(item)
                }
            }
        }
    }

    class ModernWondersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            GenericAdapter.Binder<ModernWondersListItemModel> {
        override fun bind(
            item: ModernWondersListItemModel,
            listener: OnItemClickListener<ModernWondersListItemModel>?
        ) {
            itemView.apply {
                modern_wonders_item_title.text = item.title
                Picasso.get().load(item.url).into(modern_wonders_item_thumbnail)
                setOnClickListener {
                    listener?.onItemClick(item)
                }
            }
        }
    }


}