package com.alexstephanov.wondersoftheworld.ui.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.model.ListModel
import com.alexstephanov.wondersoftheworld.ui.itemscreen.PreviewFragment1
import com.alexstephanov.wondersoftheworld.viewpager.HomePagerAdapter
import com.google.android.gms.maps.model.CameraPosition
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment(private val listModel: ListModel) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        home_pager.adapter = HomePagerAdapter(this, listModel)
        home_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when(position) {
                    0 -> {
                        activeButton(button_ancient_home)
                        clickableButton(button_modern_home)
                        clickableButton(button_nature_home)
                    }
                    1 -> {
                        activeButton(button_modern_home)
                        clickableButton(button_ancient_home)
                        clickableButton(button_nature_home)
                    }
                    2 -> {
                        activeButton(button_nature_home)
                        clickableButton(button_ancient_home)
                        clickableButton(button_modern_home)
                    }
                }
            }

            fun activeButton(activeButton: Button) {
                activeButton.setTextColor(resources.getColor(R.color.colorWhite))

                when(activeButton) {
                    button_ancient_home -> {
                        activeButton.setBackgroundResource(R.drawable.button_active_left_border)
                        button_modern_home.setOnClickListener {
                            home_pager.currentItem = 1
                        }
                        button_nature_home.setOnClickListener {
                            home_pager.currentItem = 2
                        }
                    }
                    button_modern_home -> {
                        activeButton.setBackgroundResource(R.drawable.button_active_center_border)
                        button_ancient_home.setOnClickListener {
                            home_pager.currentItem = 0
                        }
                        button_nature_home.setOnClickListener {
                            home_pager.currentItem = 2
                        }
                    }
                    button_nature_home -> {
                        activeButton.setBackgroundResource(R.drawable.button_active_right_border)
                        button_ancient_home.setOnClickListener {
                            home_pager.currentItem = 0
                        }
                        button_modern_home.setOnClickListener {
                            home_pager.currentItem = 1
                        }
                    }
                }
            }

            fun clickableButton(clickableButton: Button) {
                clickableButton.setTextColor(resources.getColor(R.color.colorBlack))

                when(clickableButton) {
                    button_ancient_home -> clickableButton.setBackgroundResource(R.drawable.button_left_border)
                    button_modern_home ->  clickableButton.setBackgroundResource(R.drawable.button_center_border)
                    button_nature_home -> clickableButton.setBackgroundResource(R.drawable.button_right_border)
                }
            }
        })
    }
}