package com.example.haris.jetpacksubmission2.project.di

import android.content.Context
import com.example.haris.jetpacksubmission2.project.data.MovieRepository
import com.example.haris.jetpacksubmission2.project.data.local.LocalDataSource
import com.example.haris.jetpacksubmission2.project.data.local.room.MovieRoomDatabase
import com.example.haris.jetpacksubmission2.project.data.remote.RemoteDataSource
import com.example.haris.jetpacksubmission2.project.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieRepository {
        val database = MovieRoomDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()
        return MovieRepository.getInstance(remoteDataSource,localDataSource,appExecutors)
    }
}