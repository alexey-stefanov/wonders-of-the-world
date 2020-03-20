package com.alexstephanov.wondersoftheworld.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.alexstephanov.wondersoftheworld.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detailed.*

class DetailedFragment : Fragment() {

    private var title: String? = null
    private var description: String? = null
    private var location: String? = null
    private var creationDate: String? = null
    private var destructionDate: String? = null
    private var latitude: String? = null
    private var longitude: String? = null
    private var url: String? = null
    private lateinit var thumbnail: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = arguments?.getString("title")
        description = arguments?.getString("description")
        location = arguments?.getString("location")
        creationDate = arguments?.getString("date_cre")
        destructionDate = arguments?.getString("date_des")
        latitude = arguments?.getString("latitude")
        longitude = arguments?.getString("longitude")
        url = arguments?.getString("url")
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

        thumbnail = view.findViewById(R.id.thumbnail_detailed)
        Picasso.get().load(url).into(thumbnail)

        val creationDateString = "Дата создания: \n$creationDate"
        val destructionDateString = "Дата разрушения: \n$destructionDate"

        title_detailed.text = title
        location_detailed.text = location
        creation_date_detailed.text = creationDateString
        destruction_date_detailed.text = destructionDateString
        description_detailed.text = description

        linear_layout_detailed.setOnClickListener{
            activity?.supportFragmentManager?.popBackStack()
        }
    }

}