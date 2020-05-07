package com.alexstephanov.wondersoftheworld.ui.mainscreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.list.GenericAdapter
import com.alexstephanov.wondersoftheworld.listeners.OnItemClickListener

class ListFragment<T>(private val itemList: List<T>, private val layoutId: Int, private var listener: OnFragmentEventListener<T>? = null) : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as OnFragmentEventListener<T>?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_list)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val dataAdapter = object : GenericAdapter<T>(itemList, object : OnItemClickListener<T> {
            override fun onItemClick(itemModel: T) {
                listener?.onItemClickEvent(itemModel)
            }
        }) {
            override fun getLayoutId(position: Int, obj: T): Int = layoutId
        }
        recyclerView.adapter = dataAdapter
        recyclerView.layoutManager = GridLayoutManager(context, 3)

    }

    interface OnFragmentEventListener<T> {
        fun onItemClickEvent(itemModel: T)
    }
}