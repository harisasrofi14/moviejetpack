package com.example.haris.jetpacksubmission2.project.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.haris.jetpacksubmission2.project.data.MovieRepository

class TvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getTvShow(): LiveData<List<TvShowEntity>> = movieRepository.getAllTvShow()
}