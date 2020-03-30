package com.alexstephanov.wondersoftheworld.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.model.AncientWondersListItemModel
import com.alexstephanov.wondersoftheworld.model.DataModel
import com.alexstephanov.wondersoftheworld.model.ListItemModel
import com.alexstephanov.wondersoftheworld.model.ModernWondersListItemModel
import com.alexstephanov.wondersoftheworld.server_api.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainActivity<T> : AppCompatActivity(), MainFragment.OnFragmentEventListener<T>, DetailedFragment.OnFragmentEventListener {

    private lateinit var result: DataModel

    private lateinit var ancientFragment: MainFragment<AncientWondersListItemModel>
    private lateinit var modernFragment: MainFragment<ModernWondersListItemModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            result = getDataFromNetwork()
            ancientFragment = MainFragment(result.listModel.ancientWonders,
                LAYOUT_ANCIENT
            )
            modernFragment = MainFragment(result.listModel.modernWonders,
                LAYOUT_MODERN
            )
            supportFragmentManager.beginTransaction().replace(R.id.fragment_place, ancientFragment).commitAllowingStateLoss()
        }
    }

    private suspend fun getDataFromNetwork() : DataModel {
        return suspendCoroutine { continuation ->
            NetworkService.getService().getCurrentData()
                .enqueue(object : Callback<DataModel>
                {
                    override fun onResponse(call: Call<DataModel>, response: Response<DataModel>)
                    {
                        val result = response.body()


                        if (result != null)
                            continuation.resume(result)
                    }

                    override fun onFailure(call: Call<DataModel>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
        }
    }

    override fun onItemClickEvent(itemModel: T) {
        val bundle = Bundle()
        if(itemModel is ListItemModel) {
            bundle.putInt("id", itemModel.id)
            bundle.putString("title", itemModel.title)
            bundle.putString("description", itemModel.description)
            bundle.putString("location", itemModel.location)
            bundle.putString("date_cre", itemModel.creationDate)
            bundle.putString("date_des", itemModel.destructionDate)
            bundle.putDouble("latitude", itemModel.latitude)
            bundle.putDouble("longitude", itemModel.longitude)
            bundle.putString("url", itemModel.url)
        }
        val fragment = DetailedFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).add(
            R.id.fragment_place, fragment).addToBackStack("detailed").commitAllowingStateLoss()
    }

    override fun onTopButtonsClickEvent(buttonId: Int) {
        when(buttonId) {
            0 -> supportFragmentManager.beginTransaction().replace(R.id.fragment_place, ancientFragment).commitAllowingStateLoss()
            1 -> supportFragmentManager.beginTransaction().replace(R.id.fragment_place, modernFragment).commitAllowingStateLoss()
        }
    }

    override fun onBackgroundClickEvent() {
        supportFragmentManager.popBackStack()
    }

    companion object {
        const val LAYOUT_ANCIENT = R.layout.ancient_wonders_list_item
        const val LAYOUT_MODERN = R.layout.modern_wonders_list_item
    }
}