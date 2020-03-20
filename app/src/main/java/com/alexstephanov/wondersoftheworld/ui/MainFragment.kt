package com.alexstephanov.wondersoftheworld.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexstephanov.wondersoftheworld.MainActivity
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.listeners.OnItemClickListener
import com.alexstephanov.wondersoftheworld.model.ListItemModel
import com.alexstephanov.wondersoftheworld.model.ListModel
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

    private suspend fun queryProcess() : ListModel {
        return suspendCoroutine { continuation ->
            NetworkService.getService().getCurrentData()
                    .enqueue(object : Callback<ListModel>
                    {
                        override fun onResponse(call: Call<ListModel>, response: Response<ListModel>)
                        {
                            val result = response.body()
                            if (result != null)
                                continuation.resume(result)
                            else
                                continuation.resume(ListModel(emptyList(), emptyList()))
                        }

                        override fun onFailure(call: Call<ListModel>, t: Throwable) {
                            continuation.resume(ListModel(emptyList(), emptyList()))
                            t.printStackTrace()
                        }
                    })
        }
    }

    private fun startQuery() {
        GlobalScope.launch(Dispatchers.Main) {
            val result: ListModel = queryProcess()

            if (result.ancient_wonders.isNotEmpty()) {
                val myAdapter = ListAdapter(result.ancient_wonders, object : OnItemClickListener {
                    override fun onItemClick(itemModel: ListItemModel) {
                        val activity = activity
                        if(activity is MainActivity)
                            activity.showDetailedFragment(itemModel)
                        }
                })

                recyclerView.adapter = myAdapter
                recyclerView.layoutManager = GridLayoutManager(context, 3)
            }
        }

    }
}