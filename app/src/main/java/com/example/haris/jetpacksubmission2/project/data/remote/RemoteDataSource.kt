package com.example.haris.jetpacksubmission2.project.data.remote

import android.util.Log
import com.example.haris.jetpacksubmission2.project.data.remote.response.MovieResponse
import com.example.haris.jetpacksubmission2.project.data.remote.response.TvShowResponse
import com.example.haris.jetpacksubmission2.project.ui.detailmovie.MovieDetailEntity
import com.example.haris.jetpacksubmission2.project.ui.detailtvshow.TvShowDetailEntity
import com.example.haris.jetpacksubmission2.project.ui.movie.MovieEntity
import com.example.haris.jetpacksubmission2.project.ui.tvshow.TvShowEntity
import com.example.haris.jetpacksubmission2.project.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {


    companion object {
        private var key = "40ebbdf7ace0aedc176087ca768e21e2"
        private var language = "en-US"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }

    }

    fun getAllMovie(callback: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        val list = ArrayList<MovieEntity>()
        val client = ApiConfig.getApiService().getMovies(
            key, language, "popularity.desc",
            includeAdult = false,
            includeVideo = false,
            page = 1
        )
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    Log.d("getMovie", "onresponse: ${response.body()}")
                    response.body()?.results?.let { list.addAll(it) }
                    EspressoIdlingResource.decrement()
                    callback.onAllMovieReceived(list)
                } else {
                    Log.e("getMovie", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("getMovie", "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getAllTvShow(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        val list = ArrayList<TvShowEntity>()
        val client = ApiConfig.getApiService().getTvShows(
            key, language, "popularity.desc",
            "America/New_York", false,
            1
        )
        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.results?.let { list.addAll(it) }
                    EspressoIdlingResource.decrement()
                    callback.onAllTvShowReceived(list)
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e("getTvShows", "onFailure: ${t.message.toString()}")
            }

        })

    }

    fun getDetailMovie(callback: LoadDetailMovieCallback, id: Int) {
        EspressoIdlingResource.increment()
        var movie = MovieDetailEntity()
        val client = ApiConfig.getApiService().getDetailMovie(
            movieId = id,
            key, language
        )

        client.enqueue(object : Callback<MovieDetailEntity> {


            override fun onResponse(
                call: Call<MovieDetailEntity>,
                response: Response<MovieDetailEntity>
            ) {
                Log.d("getDetailMovie", response.raw().request.url.toString())
                if (response.isSuccessful) {
                    response.body()?.let { movie = it }
                    EspressoIdlingResource.decrement()
                    callback.onDetailMovieReceive(movie)
                }
            }

            override fun onFailure(call: Call<MovieDetailEntity>, t: Throwable) {
                Log.e("getDetailMovie", "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getDetailTvShow(callback: LoadDetailTvShowCallback, id: Int) {
        EspressoIdlingResource.increment()
        var tv = TvShowDetailEntity()
        val client = ApiConfig.getApiService().getDetailTvShow(
            tvId = id,
            key, language
        )
        client.enqueue(object : Callback<TvShowDetailEntity> {
            override fun onResponse(
                call: Call<TvShowDetailEntity>,
                response: Response<TvShowDetailEntity>
            ) {
                Log.d("getDetailTvShow", "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    response.body()?.let { tv = it }
                    EspressoIdlingResource.decrement()
                    callback.onDetailTvShowReceive(tv)
                }
            }

            override fun onFailure(call: Call<TvShowDetailEntity>, t: Throwable) {
                Log.e("getDetailMovie", "onFailure: ${t.message.toString()}")
            }
        })
    }

    interface LoadMovieCallback {
        fun onAllMovieReceived(movieResponse: List<MovieEntity>)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShowResponse: List<TvShowEntity>)

    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieReceive(detailMovieResponse: MovieDetailEntity)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowReceive(detailTvShowResponse: TvShowDetailEntity)
    }
}