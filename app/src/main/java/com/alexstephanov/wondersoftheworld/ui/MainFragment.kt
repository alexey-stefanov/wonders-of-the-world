package com.alexstephanov.wondersoftheworld.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.listeners.OnItemClickListener
import com.alexstephanov.wondersoftheworld.model.ListItemModel
import com.alexstephanov.wondersoftheworld.model.ListModel

class MainFragment(listModel: ListModel?, private val fragmentId: Int, private var listener: OnFragmentEventListener? = null) : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val itemsList = when(fragmentId) {
        0 -> listModel?.ancientWonders
        1 -> listModel?.modernWonders
        else -> null
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as OnFragmentEventListener
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

        when(fragmentId) {
            0 -> {
                buttonModern.setOnClickListener {
                    listener?.onTopButtonsClickEvent(BUTTON_MODERN)
                }
            }
            1 -> {
                buttonAncient.setOnClickListener {
                    listener?.onTopButtonsClickEvent(BUTTON_ANCIENT)
                }
            }
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (itemsList != null) {
            val myAdapter = ListAdapter(itemsList, object : OnItemClickListener {
                override fun onItemClick(itemModel: ListItemModel) {
                    listener?.onItemClickEvent(itemModel)
                }
            })
            recyclerView.adapter = myAdapter
            recyclerView.layoutManager = GridLayoutManager(context, 3)
        }

    }

    interface OnFragmentEventListener {
        fun onItemClickEvent(itemModel: ListItemModel)
        fun onTopButtonsClickEvent(buttonId: Int)
    }

    companion object {
        const val BUTTON_ANCIENT = 0
        const val BUTTON_MODERN = 1
    }
}