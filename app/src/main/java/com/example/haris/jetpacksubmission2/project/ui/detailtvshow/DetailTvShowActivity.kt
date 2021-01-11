package com.example.haris.jetpacksubmission2.project.ui.detailtvshow

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.haris.jetpacksubmission2.R
import com.example.haris.jetpacksubmission2.project.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        val tvId = intent.getIntExtra("tvId", 0)
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[TvShowDetailViewModel::class.java]
        progress_bar.visibility = View.VISIBLE
        tv_detail_title.visibility = View.INVISIBLE
        tv_rating.visibility = View.INVISIBLE
        rating_bar.visibility = View.INVISIBLE
        tv_overview.visibility = View.INVISIBLE
        viewModel.getDetailTvShow(tvId).observe(this, Observer { detailTvShow ->
            initView(detailTvShow)
            progress_bar.visibility = View.GONE
            tv_detail_title.visibility = View.VISIBLE
            tv_rating.visibility = View.VISIBLE
            rating_bar.visibility = View.VISIBLE
            tv_overview.visibility = View.VISIBLE
        })
    }

    private fun initView(data: TvShowDetailEntity) {
        tv_detail_title.text = data.name
        tv_rating.text = data.voteAverage.toString()
        rating_bar.rating = (data.voteAverage!! * 0.5).toFloat()
        tv_overview.text = data.overview
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + data.backdropPath)
            .centerCrop()
            .into(iv_backdrop)
    }
}