package com.example.haris.jetpacksubmission2.project.data.local

import androidx.lifecycle.LiveData
import com.example.haris.jetpacksubmission2.project.data.local.entity.Movie
import com.example.haris.jetpacksubmission2.project.data.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

    fun getAllFavoriteMovie() : LiveData<List<Movie>> = mMovieDao.getAllFavoriteMovie()

    fun setFavoriteMovie(movie: Movie) = mMovieDao.insertFavoriteMovie(movie)
}