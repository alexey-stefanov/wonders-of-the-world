package com.alexstephanov.wondersoftheworld.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.list.GenericAdapter
import com.alexstephanov.wondersoftheworld.listeners.OnItemClickListener

class MainFragment<T>(private val itemList: List<T>, private val layoutId: Int, private var listener: OnFragmentEventListener<T>? = null) : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView = view!!.findViewById(R.id.recycler_view_main_list)
        val buttonAncient: Button = view.findViewById(R.id.button_ancient_main)
        val buttonModern: Button = view.findViewById(R.id.button_modern_main)
        val buttonNature: Button = view.findViewById(R.id.button_nature_main)

        when(layoutId) {
            R.layout.ancient_wonders_list_item -> {
                buttonModern.setOnClickListener {
                    listener?.onTopButtonsClickEvent(BUTTON_MODERN)
                }
                buttonNature.setOnClickListener {
                    listener?.onTopButtonsClickEvent(BUTTON_NATURE)
                }
            }
            R.layout.modern_wonders_list_item -> {
                buttonAncient.setOnClickListener {
                    listener?.onTopButtonsClickEvent(BUTTON_ANCIENT)
                }
                buttonNature.setOnClickListener {
                    listener?.onTopButtonsClickEvent(BUTTON_NATURE)
                }
            }
            R.layout.nature_wonders_list_item -> {
                buttonAncient.setOnClickListener {
                    listener?.onTopButtonsClickEvent(BUTTON_ANCIENT)
                }
                buttonModern.setOnClickListener {
                    listener?.onTopButtonsClickEvent(BUTTON_MODERN)
                }
            }
        }

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
        fun onTopButtonsClickEvent(buttonId: Int)
    }

    companion object {
        const val BUTTON_ANCIENT = 0
        const val BUTTON_MODERN = 1
        const val BUTTON_NATURE = 2
    }
}