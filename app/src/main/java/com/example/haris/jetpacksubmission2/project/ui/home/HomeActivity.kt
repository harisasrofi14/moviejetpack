package com.example.haris.jetpacksubmission2.project.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.haris.jetpacksubmission2.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homePagerAdapter = HomePagerAdapter(this, supportFragmentManager)
        view_pager.adapter = homePagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
    }
}