package com.alexstephanov.wondersoftheworld.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.alexstephanov.wondersoftheworld.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detailed.*

class DetailedFragment(private var listener: OnFragmentEventListener? = null) : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap

    private var id: Int? = null
    private var name: String? = null
    private var description: String? = null
    private var location: String? = null
    private var creationDate: String? = null
    private var destructionDate: String? = null
    private var latitude: Double? = null
    private var longitude: Double? = null
    private var url: String? = null
    private lateinit var thumbnail: ImageView

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as OnFragmentEventListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = arguments?.getInt("id") ?: 0
        name = arguments?.getString("name") ?: ""
        description = arguments?.getString("description") ?: ""
        location = arguments?.getString("location") ?: ""
        creationDate = arguments?.getString("date_cre") ?: ""
        destructionDate = arguments?.getString("date_des") ?: ""
        latitude = arguments?.getDouble("latitude") ?: 0.0
        longitude = arguments?.getDouble("longitude") ?: 0.0
        url = arguments?.getString("url") ?: ""
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

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map_detailed) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        thumbnail = view.findViewById(R.id.thumbnail_detailed)
        Picasso.get().load(url).into(thumbnail)

        val creationDateString = "$creationDate"
        val destructionDateString = "$destructionDate"

        name_detailed.text = name
        location_detailed.text = location
        creation_date_detailed.text = creationDateString
        destruction_date_detailed.text = destructionDateString
        description_detailed.text = description

        linear_layout_detailed.setOnClickListener{
            listener?.onBackgroundClickEvent()
        }
    }

    interface OnFragmentEventListener {
        fun onBackgroundClickEvent()
    }

    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0!!
        val marker = LatLng(latitude!!, longitude!!)
        val cameraPosition = CameraPosition.builder()
            .target(marker)
            .zoom(15f)
            .build()
        googleMap.addMarker(MarkerOptions().position(marker).title(name))
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

}