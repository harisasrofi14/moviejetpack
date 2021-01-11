package com.example.haris.jetpacksubmission2.project.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.haris.jetpacksubmission2.project.data.MovieRepository

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovie(): LiveData<List<MovieEntity>> = movieRepository.getAllMovie()
}