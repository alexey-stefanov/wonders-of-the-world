package com.alexstephanov.wondersoftheworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.alexstephanov.wondersoftheworld.model.ListItemModel
import com.alexstephanov.wondersoftheworld.ui.DetailedFragment
import com.alexstephanov.wondersoftheworld.ui.MainFragment

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_place, MainFragment()).commitAllowingStateLoss()
        }
    }

    override fun onClick(v: View?) {
        this.onBackPressed()
    }

    fun showDetailedFragment(itemModel: ListItemModel) {
        val bundle = Bundle()
        bundle.putString("title", itemModel.title)
        bundle.putString("description", itemModel.description)
        bundle.putString("location", itemModel.location)
        bundle.putString("date_cre", itemModel.creationDate)
        bundle.putString("date_des", itemModel.destructionDate)
        bundle.putString("latitude", itemModel.latitude)
        bundle.putString("longitude", itemModel.longitude)
        bundle.putString("url", itemModel.url)
        val fragment = DetailedFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).add(R.id.fragment_place, fragment).addToBackStack("detailed").commitAllowingStateLoss()

    }
}