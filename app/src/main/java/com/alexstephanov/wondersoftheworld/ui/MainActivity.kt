package com.alexstephanov.wondersoftheworld.ui

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.database.WondersDao
import com.alexstephanov.wondersoftheworld.database.WondersDatabase
import com.alexstephanov.wondersoftheworld.model.*
import com.alexstephanov.wondersoftheworld.server_api.NetworkService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainActivity<T> : AppCompatActivity(), MainFragment.OnFragmentEventListener<T>, DetailedFragment.OnFragmentEventListener, NatureWondersDetailedFragment.OnFragmentEventListener {

    private var ancientFragment: MainFragment<AncientWondersListItemModel>? = null
    private var modernFragment: MainFragment<ModernWondersListItemModel>? = null
    private var natureFragment: MainFragment<NatureWondersListItemModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wondersDao = WondersDatabase.getInstance(this).getWondersDao()

        if(checkNetworkConnection(this)) {

            GlobalScope.launch {
               val result = getDataFromNetwork()

                GlobalScope.launch(Dispatchers.IO) {
                    wondersDao.deleteAllAncientWondersItems()
                    wondersDao.deleteAllModernWondersItems()
                    wondersDao.deleteAllNatureWondersItems()

                    wondersDao.addAncientsWondersItems(result.listModel.ancientWonders)
                    wondersDao.addModernWondersItems(result.listModel.modernWonders)
                    wondersDao.addNatureWondersItems(result.listModel.natureWonders)

                    ancientFragment = MainFragment(result.listModel.ancientWonders,
                        LAYOUT_ANCIENT
                    )
                    modernFragment = MainFragment(result.listModel.modernWonders,
                        LAYOUT_MODERN
                    )
                    natureFragment = MainFragment(result.listModel.natureWonders,
                        LAYOUT_NATURE)
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_place, ancientFragment!!).commitAllowingStateLoss()

                }

                /*ancientFragment = MainFragment(result.listModel.ancientWonders,
                    LAYOUT_ANCIENT
                )
                modernFragment = MainFragment(result.listModel.modernWonders,
                    LAYOUT_MODERN
                )
                natureFragment = MainFragment(result.listModel.natureWonders,
                    LAYOUT_NATURE)
                supportFragmentManager.beginTransaction().replace(R.id.fragment_place, ancientFragment!!).commitAllowingStateLoss()
   */         }

        } else {
            text_view_network_main.text = "Оффлайн режим"
            text_view_network_main.setBackgroundResource(R.color.colorRed)
            text_view_network_main.visibility = View.VISIBLE

            GlobalScope.launch(Dispatchers.IO) {
                val result = getDataFromDatabase(wondersDao)

                if(result.ancientWonders.isNotEmpty() and
                        result.modernWonders.isNotEmpty() and
                        result.natureWonders.isNotEmpty()) {

                    ancientFragment = MainFragment(result.ancientWonders,
                        LAYOUT_ANCIENT
                    )
                    modernFragment = MainFragment(result.modernWonders,
                        LAYOUT_MODERN
                    )
                    natureFragment = MainFragment(result.natureWonders,
                        LAYOUT_NATURE)
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_place, ancientFragment!!).commitAllowingStateLoss()
                } else {
                    showNetworkLostConnectionTextView("Не удалось загрузить данные! Проверьте подключение к интернету!")
                }
            }
        }

        /*GlobalScope.launch(Dispatchers.IO) {
            if (wondersDao.getAllAncientWondersItems().isNotEmpty() and
                    wondersDao.getAllModernWondersItems().isNotEmpty() and
                    wondersDao.getAllNatureWondersItems().isNotEmpty()) {

                ancientFragment = MainFragment(wondersDao.getAllAncientWondersItems(), LAYOUT_ANCIENT)
                modernFragment = MainFragment(wondersDao.getAllModernWondersItems(), LAYOUT_MODERN)
                natureFragment = MainFragment(wondersDao.getAllNatureWondersItems(), LAYOUT_NATURE)

                supportFragmentManager.beginTransaction().replace(R.id.fragment_place, ancientFragment).commitAllowingStateLoss()
            } else
                startQuery()
        }*/
    }

    private fun checkNetworkConnection(context: Context):Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return  networkInfo != null && networkInfo.isConnected
    }

    private fun showNetworkLostConnectionTextView(message: String) {
        text_view_network_main.text = message
        text_view_network_main.setBackgroundResource(R.color.colorRed)
        text_view_network_main.visibility = View.VISIBLE
    }

    /*private fun startQuery() {

        GlobalScope.launch(Dispatchers.Main) {
            result = getDataFromNetwork()
            if(result.listModel.ancientWonders.isNotEmpty() and
                    result.listModel.modernWonders.isNotEmpty() and
                    result.listModel.natureWonders.isNotEmpty()) {

                wondersDao.addAncientsWondersItems(result.listModel.ancientWonders)
                wondersDao.addModernWondersItems(result.listModel.modernWonders)
                wondersDao.addNatureWondersItems(result.listModel.natureWonders)

            } else {
                Toast.makeText(this@MainActivity, "empty list", Toast.LENGTH_SHORT).show()
            }
        }

        GlobalScope.launch(Dispatchers.IO) {
            ancientFragment = MainFragment(wondersDao.getAllAncientWondersItems(), LAYOUT_ANCIENT)
            modernFragment = MainFragment(wondersDao.getAllModernWondersItems(), LAYOUT_MODERN)
            natureFragment = MainFragment(wondersDao.getAllNatureWondersItems(), LAYOUT_NATURE)
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_place, ancientFragment).commitAllowingStateLoss()

    }*/

    private suspend fun getDataFromDatabase(wondersDao: WondersDao) : ListModel {
        return suspendCoroutine { continuation ->
            continuation.resume(ListModel(
                    wondersDao.getAllAncientWondersItems(),
                    wondersDao.getAllModernWondersItems(),
                    wondersDao.getAllNatureWondersItems()
            ))
        }
    }

    private suspend fun getDataFromNetwork() : DataModel {
        return suspendCoroutine { continuation ->
            NetworkService.getService().getCurrentData()
                .enqueue(object : Callback<DataModel>
                {
                    override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {

                        val result = response.body()

                        if (result != null)
                            continuation.resume(result)
                        else
                            continuation.resume(DataModel(ListModel(emptyList(), emptyList(), emptyList())))
                    }

                    override fun onFailure(call: Call<DataModel>, t: Throwable) {
                        t.printStackTrace()
                        continuation.resume(DataModel(ListModel(emptyList(), emptyList(), emptyList())))
                    }
                })
        }
    }

    override fun onItemClickEvent(itemModel: T) {
        val bundle = Bundle()
        var fragment: Fragment? = null
        when(itemModel) {
            is ListItemModel -> {
                bundle.putInt("id", itemModel.id)
                bundle.putString("name", itemModel.name)
                bundle.putString("description", itemModel.description)
                bundle.putString("location", itemModel.location)
                bundle.putString("date_cre", itemModel.creationDate)
                bundle.putString("date_des", itemModel.destructionDate)
                bundle.putDouble("latitude", itemModel.latitude)
                bundle.putDouble("longitude", itemModel.longitude)
                bundle.putString("url", itemModel.url)
                fragment = DetailedFragment()
            }
            is NatureWondersListItemModel -> {
                bundle.putInt("id", itemModel.id)
                bundle.putString("name", itemModel.name)
                bundle.putString("description", itemModel.description)
                bundle.putString("type", itemModel.type)
                bundle.putString("location", itemModel.location)
                bundle.putDouble("latitude", itemModel.latitude)
                bundle.putDouble("longitude", itemModel.longitude)
                bundle.putString("url", itemModel.url)
                fragment = NatureWondersDetailedFragment()
            }
        }
        fragment?.arguments = bundle
        if (fragment != null) {
            supportFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).add(
                R.id.fragment_place, fragment).addToBackStack("detailed").commitAllowingStateLoss()
        }
    }

    override fun onTopButtonsClickEvent(buttonId: Int) {
        when(buttonId) {
            0 -> supportFragmentManager.beginTransaction().replace(R.id.fragment_place, ancientFragment!!).commitAllowingStateLoss()
            1 -> supportFragmentManager.beginTransaction().replace(R.id.fragment_place, modernFragment!!).commitAllowingStateLoss()
            2 -> supportFragmentManager.beginTransaction().replace(R.id.fragment_place, natureFragment!!).commitAllowingStateLoss()
        }
    }

    override fun onBackgroundClickEvent() {
        supportFragmentManager.popBackStack()
    }

    companion object {
        const val LAYOUT_ANCIENT = R.layout.ancient_wonders_list_item
        const val LAYOUT_MODERN = R.layout.modern_wonders_list_item
        const val LAYOUT_NATURE = R.layout.nature_wonders_list_item
    }
}