package com.alexstephanov.wondersoftheworld.viewpager

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alexstephanov.wondersoftheworld.model.ListItemModel
import com.alexstephanov.wondersoftheworld.ui.MainActivity
import com.alexstephanov.wondersoftheworld.ui.itemscreen.DescriptionFragment
import com.alexstephanov.wondersoftheworld.ui.itemscreen.MapFragment
import com.alexstephanov.wondersoftheworld.ui.itemscreen.PreviewFragment1
import com.alexstephanov.wondersoftheworld.ui.itemscreen.PreviewFragment2

class DetailedPagerAdapter(fragment: Fragment, private val listItemModel: ListItemModel) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        Log.e("Detail", "${listItemModel.type} and ${listItemModel.creationDate} and ${listItemModel.destructionDate}")
        val descriptionFragment =
            DescriptionFragment(
                description = listItemModel.description
            )
        val mapFragment =
            MapFragment(
                latitude = listItemModel.latitude,
                longitude = listItemModel.longitude
            )
        Log.e("Detail", listItemModel.type)
        return when(position) {
            0 -> {
                if(listItemModel.type == "default") {
                    PreviewFragment1(
                        name = listItemModel.name,
                        location = listItemModel.location,
                        creationDate = listItemModel.creationDate,
                        destructionDate = listItemModel.destructionDate,
                        url = listItemModel.url
                    )
                } else {
                    PreviewFragment2(
                        name = listItemModel.name,
                        location = listItemModel.location,
                        type = listItemModel.type,
                        url = listItemModel.url
                    )
                }
            }
            1 -> descriptionFragment
            2 -> mapFragment
            else -> Fragment()
        }
    }
}