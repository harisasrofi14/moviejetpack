package com.example.haris.jetpacksubmission2.project.data.remote

import com.example.haris.jetpacksubmission2.project.data.remote.response.MovieResponse
import com.example.haris.jetpacksubmission2.project.data.remote.response.TvShowResponse
import com.example.haris.jetpacksubmission2.project.ui.detailmovie.MovieDetailEntity
import com.example.haris.jetpacksubmission2.project.ui.detailtvshow.TvShowDetailEntity

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("include_video") includeVideo: Boolean,
        @Query("page") page: Int
    ): Call<MovieResponse>


    @GET("discover/tv")
    fun getTvShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("timezone") timezone: String,
        @Query("include_null_first_air_dates") includeNullFirstAir: Boolean,
        @Query("page") page: Int
    ): Call<TvShowResponse>


    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<MovieDetailEntity>


    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<TvShowDetailEntity>
}