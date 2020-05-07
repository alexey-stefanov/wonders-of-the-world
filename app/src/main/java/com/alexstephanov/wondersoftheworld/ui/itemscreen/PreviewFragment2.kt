package com.alexstephanov.wondersoftheworld.ui.itemscreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.listeners.OnFragmentEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_preview_1.*
import kotlinx.android.synthetic.main.fragment_preview_2.*

class PreviewFragment2(var listener: OnFragmentEventListener? = null, val name: String, val location: String, val type: String, val url: String) : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as OnFragmentEventListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preview_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("Detail", "FragPrev2")

        val thumbnail: ImageView = view.findViewById(R.id.thumbnail_preview_2)
        Picasso.get().load(url).into(thumbnail)

        name_preview_2.text = name
        location_preview_2.text = location
        type_preview_2.text = type

        frame_layout_preview_2.setOnClickListener{
            listener?.onBackgroundClickEvent()
        }
    }

}