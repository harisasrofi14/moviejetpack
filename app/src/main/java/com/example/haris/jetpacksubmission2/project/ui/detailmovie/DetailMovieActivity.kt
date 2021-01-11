package com.example.haris.jetpacksubmission2.project.ui.detailmovie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.haris.jetpacksubmission2.R
import com.example.haris.jetpacksubmission2.project.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        val movieId = intent.getIntExtra("movieId", 0)
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]
        progress_bar.visibility = View.VISIBLE
        tv_detail_title.visibility = View.INVISIBLE
        tv_rating.visibility = View.INVISIBLE
        rating_bar.visibility = View.INVISIBLE
        tv_overview.visibility = View.INVISIBLE

        viewModel.getDetailMovie(movieId).observe(this, Observer { detailMovie ->
            initView(detailMovie)
            progress_bar.visibility = View.GONE
            tv_detail_title.visibility = View.VISIBLE
            tv_rating.visibility = View.VISIBLE
            rating_bar.visibility = View.VISIBLE
            tv_overview.visibility = View.VISIBLE
        })
    }

    private fun initView(movieDetailEntity: MovieDetailEntity) {
        tv_detail_title.text = movieDetailEntity.title
        tv_rating.text = movieDetailEntity.voteAverage.toString()
        tv_overview.text = movieDetailEntity.overview.toString()
        movieDetailEntity.voteAverage?.let {
            rating_bar.rating = (it* 0.5).toFloat()
        }
       // rating_bar.rating = (movieDetailEntity.voteAverage!! * 0.5).toFloat()
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + movieDetailEntity.backdropPath)
            .centerCrop()
            .into(iv_backdrop)
    }
}