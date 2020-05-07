package com.alexstephanov.wondersoftheworld.ui.itemscreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.listeners.OnFragmentEventListener
import kotlinx.android.synthetic.main.fragment_description.*
import kotlinx.android.synthetic.main.fragment_preview_1.*

class DescriptionFragment(var listener: OnFragmentEventListener? = null, val description: String): Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as OnFragmentEventListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        description_detailed.text = description

        frame_layout_description.setOnClickListener{
            listener?.onBackgroundClickEvent()
        }
    }

}