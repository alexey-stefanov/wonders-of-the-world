package com.alexstephanov.wondersoftheworld.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.listeners.OnItemClickListener
import com.alexstephanov.wondersoftheworld.model.ListItem

class MainFragment : Fragment(), OnItemClickListener {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = arrayListOf(
            ListItem("item1", "item1"),
            ListItem("item2", "item2"),
            ListItem("item3", "item3"),
            ListItem("item4", "item4"),
            ListItem("item5", "item5"),
            ListItem("item6", "item6"),
            ListItem("item7", "item7")
        )

        val myAdapter = ListAdapter(items, object : OnItemClickListener {
            override fun onItemClick(item: ListItem) {
                val bundle = Bundle()
                bundle.putString("title", item.title)
                bundle.putString("description", item.description)
                val fragment = DetailedFragment()
                fragment.arguments = bundle
                fragmentManager!!.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).add(R.id.fragment_place, fragment).addToBackStack("detailed").commitAllowingStateLoss()
            }
        })

        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onItemClick(item: ListItem) {
        val bundle = Bundle()
        bundle.putString("title", item.title)
        bundle.putString("description", item.description)
        val fragment = DetailedFragment()
        fragment.arguments = bundle
        childFragmentManager.beginTransaction().add(R.id.fragment_place, fragment).addToBackStack("detailed").commitAllowingStateLoss()
    }
}