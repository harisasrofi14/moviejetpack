package com.example.haris.jetpacksubmission2.project.data

import androidx.lifecycle.LiveData
import com.example.haris.jetpacksubmission2.project.data.local.entity.Movie
import com.example.haris.jetpacksubmission2.project.ui.detailmovie.MovieDetailEntity
import com.example.haris.jetpacksubmission2.project.ui.detailtvshow.TvShowDetailEntity
import com.example.haris.jetpacksubmission2.project.ui.movie.MovieEntity
import com.example.haris.jetpacksubmission2.project.ui.tvshow.TvShowEntity

interface MovieDataSource {
    fun getAllMovie(): LiveData<List<MovieEntity>>
    fun getAllTvShow() : LiveData<List<TvShowEntity>>
    fun getDetailMovie(movieId:Int) : LiveData<MovieDetailEntity>
    fun getDetailTvShow(tvId:Int) : LiveData<TvShowDetailEntity>
    fun getFavoriteMovie()  : LiveData<List<Movie>>
    fun setFavoriteMovie(movie: Movie)
}