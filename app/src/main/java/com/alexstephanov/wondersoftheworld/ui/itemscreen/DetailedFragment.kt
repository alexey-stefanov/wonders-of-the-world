package com.alexstephanov.wondersoftheworld.ui.itemscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.model.ListItemModel
import com.alexstephanov.wondersoftheworld.viewpager.DetailedPagerAdapter
import kotlinx.android.synthetic.main.fragment_detailed.*

class DetailedFragment : Fragment() {

    private var id: Int? = null
    private var name: String? = null
    private var description: String? = null
    private var type: String? = null
    private var location: String? = null
    private var creationDate: String? = null
    private var destructionDate: String? = null
    private var latitude: Double? = null
    private var longitude: Double? = null
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = arguments?.getInt("id") ?: 0
        name = arguments?.getString("name") ?: "default"
        description = arguments?.getString("description") ?: "default"
        location = arguments?.getString("location") ?: "default"
        type = arguments?.getString("type") ?: "default"
        creationDate = arguments?.getString("date_cre") ?: "default"
        destructionDate = arguments?.getString("date_des") ?: "default"
        latitude = arguments?.getDouble("latitude") ?: 0.0
        longitude = arguments?.getDouble("longitude") ?: 0.0
        url = arguments?.getString("url") ?: "default"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listItemModel = ListItemModel(id!!, name!!, description!!, location!!, type!!, creationDate!!, destructionDate!!, latitude!!, longitude!!, url!!)
        detailed_pager.adapter = DetailedPagerAdapter(this, listItemModel)
    }
}