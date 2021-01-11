package com.example.haris.jetpacksubmission2.project.ui.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.haris.jetpacksubmission2.project.data.MovieRepository


class DetailMovieViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {
    fun getDetailMovie(movieId: Int): LiveData<MovieDetailEntity> =
        movieRepository.getDetailMovie(movieId)
}