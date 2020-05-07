package com.alexstephanov.wondersoftheworld.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.listeners.OnItemClickListener
import com.alexstephanov.wondersoftheworld.model.AncientWondersItemModel
import com.alexstephanov.wondersoftheworld.model.ModernWondersItemModel
import com.alexstephanov.wondersoftheworld.model.NatureWondersListItemModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.ancient_wonders_list_item.view.*
import kotlinx.android.synthetic.main.modern_wonders_list_item.view.*
import kotlinx.android.synthetic.main.nature_wonders_list_item.view.*
import java.lang.Exception

object ViewHolderFactory {
    fun create(view: View, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.ancient_wonders_list_item -> AncientWondersViewHolder(view)
            R.layout.modern_wonders_list_item -> ModernWondersViewHolder(view)
            R.layout.nature_wonders_list_item -> NatureWondersViewHolder(view)
            else -> throw Exception("Wrong view type")
        }
    }

    class AncientWondersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            GenericAdapter.Binder<AncientWondersItemModel> {
        override fun bind(
            item: AncientWondersItemModel,
            listener: OnItemClickListener<AncientWondersItemModel>?
        ) {
            itemView.apply {
                ancient_wonders_item_title.text = item.name
                Picasso.get().load(item.url).into(ancient_wonders_item_thumbnail)
                setOnClickListener {
                    listener?.onItemClick(item)
                }
            }
        }
    }

    class ModernWondersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            GenericAdapter.Binder<ModernWondersItemModel> {
        override fun bind(
            item: ModernWondersItemModel,
            listener: OnItemClickListener<ModernWondersItemModel>?
        ) {
            itemView.apply {
                modern_wonders_item_title.text = item.name
                Picasso.get().load(item.url).into(modern_wonders_item_thumbnail)
                setOnClickListener {
                    listener?.onItemClick(item)
                }
            }
        }
    }

    class NatureWondersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericAdapter.Binder<NatureWondersListItemModel> {
        override fun bind(
            item: NatureWondersListItemModel,
            listener: OnItemClickListener<NatureWondersListItemModel>?
        ) {
            itemView.apply {
                nature_wonders_item_title.text = item.name
                Picasso.get().load(item.url).into(nature_wonders_item_thumbnail)
                setOnClickListener {
                    listener?.onItemClick(item)
                }
            }
        }
    }

}