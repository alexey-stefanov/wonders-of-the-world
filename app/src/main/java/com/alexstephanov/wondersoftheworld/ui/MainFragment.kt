package com.alexstephanov.wondersoftheworld.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.listeners.OnItemClickListener
import com.alexstephanov.wondersoftheworld.model.ListItemModel
import com.alexstephanov.wondersoftheworld.server_api.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView = view!!.findViewById(R.id.recycler_view_main_list)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        startQuery()
    }

    private suspend fun queryProcess() : List<ListItemModel> {
        return suspendCoroutine { continuation ->
            NetworkService.getService().getCurrentData()
                    .enqueue(object : Callback<List<ListItemModel>>
                    {
                        override fun onResponse(call: Call<List<ListItemModel>>, response: Response<List<ListItemModel>>)
                        {
                            val result = response.body()
                            if (result != null)
                                continuation.resume(result)
                            else
                                continuation.resume(emptyList())
                        }

                        override fun onFailure(call: Call<List<ListItemModel>>, t: Throwable) {
                            continuation.resume(emptyList())
                            t.printStackTrace()
                        }
                    })
        }
    }

    private fun startQuery() {

        GlobalScope.launch(Dispatchers.Main) {
            val result: List<ListItemModel> = queryProcess()
            Log.e("wer", result[0].title)

            if (result.isNotEmpty()) {
                val myAdapter = ListAdapter(result, object : OnItemClickListener {
                    override fun onItemClick(itemModel: ListItemModel) {
                        val bundle = Bundle()
                        bundle.putString("title", itemModel.title)
                        bundle.putString("description", itemModel.description)
                        bundle.putString("location", itemModel.description)
                        bundle.putString("date_cre", itemModel.creationDate)
                        bundle.putString("date_des", itemModel.destructionDate)
                        bundle.putString("latitude", itemModel.latitude)
                        bundle.putString("longitude", itemModel.longitude)
                        bundle.putString("url", itemModel.url)
                        val fragment = DetailedFragment()
                        fragment.arguments = bundle
                        fragmentManager!!.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).add(R.id.fragment_place, fragment).addToBackStack("detailed").commitAllowingStateLoss()
                    }
                })

                recyclerView.adapter = myAdapter
                recyclerView.layoutManager = GridLayoutManager(context, 3)
            }
        }

    }
}