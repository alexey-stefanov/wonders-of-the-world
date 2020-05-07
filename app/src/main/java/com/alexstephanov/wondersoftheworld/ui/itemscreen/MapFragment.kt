package com.alexstephanov.wondersoftheworld.ui.itemscreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.listeners.OnFragmentEventListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_preview_1.*

class MapFragment(var listener: OnFragmentEventListener? = null, val latitude: Double, val longitude: Double): Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as OnFragmentEventListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        frame_layout_map.setOnClickListener{
            listener?.onBackgroundClickEvent()
        }

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0!!
        val marker = LatLng(latitude, longitude)
        val cameraPosition = CameraPosition.builder()
            .target(marker)
            .zoom(15f)
            .build()
        googleMap.addMarker(MarkerOptions().position(marker))
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
}