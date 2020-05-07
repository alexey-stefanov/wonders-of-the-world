package com.alexstephanov.wondersoftheworld.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.model.ListModel
import com.alexstephanov.wondersoftheworld.ui.mainscreen.ListFragment

class HomePagerAdapter(fragment: Fragment, private val listModel: ListModel) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when(position) {
        0 -> ListFragment(
            listModel.ancientWonders,
            R.layout.ancient_wonders_list_item
        )
        1 -> ListFragment(
            listModel.modernWonders,
            R.layout.modern_wonders_list_item
        )
        2 -> ListFragment(
            listModel.natureWonders,
            R.layout.nature_wonders_list_item
        )
        else -> Fragment()
    }
}