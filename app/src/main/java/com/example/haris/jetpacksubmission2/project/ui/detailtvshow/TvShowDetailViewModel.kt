package com.example.haris.jetpacksubmission2.project.ui.detailtvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.haris.jetpacksubmission2.project.data.MovieRepository


class TvShowDetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getDetailTvShow(tvId: Int): LiveData<TvShowDetailEntity> =
        movieRepository.getDetailTvShow(tvId)
}