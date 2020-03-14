package com.alexstephanov.wondersoftheworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alexstephanov.wondersoftheworld.ui.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_place, MainFragment()).commitAllowingStateLoss()
        }
    }
}