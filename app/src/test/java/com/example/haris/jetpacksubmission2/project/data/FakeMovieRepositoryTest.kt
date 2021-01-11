package com.example.haris.jetpacksubmission2.project.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.haris.jetpacksubmission2.project.data.remote.RemoteDataSource
import com.example.haris.jetpacksubmission2.project.ui.detailmovie.MovieDetailEntity
import com.example.haris.jetpacksubmission2.project.ui.detailtvshow.TvShowDetailEntity
import com.example.haris.jetpacksubmission2.project.ui.movie.MovieEntity
import com.example.haris.jetpacksubmission2.project.ui.tvshow.TvShowEntity

class FakeMovieRepositoryTest(private val remoteDataSource: RemoteDataSource) : MovieDataSource {

    override fun getAllMovie(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMovie(object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMovieReceived(movieResponse: List<MovieEntity>) {
                val movieList = ArrayList<MovieEntity>()

                for (data in movieResponse) {
                    val movie = MovieEntity(
                        data.popularity,
                        data.voteCount,
                        data.video,
                        data.posterPath,
                        data.id,
                        data.adult,
                        data.backdropPath,
                        data.originalLanguage,
                        data.originalTitle,
                        data.title,
                        data.voteAverage,
                        data.overview,
                        data.releaseDate
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }

    override fun getAllTvShow(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowResponse: List<TvShowEntity>) {
                val movieList = ArrayList<TvShowEntity>()
                for (data in tvShowResponse) {
                    val tvShow = TvShowEntity()
                    tvShow.backdropPath = data.backdropPath
                    tvShow.firstAirDate = data.firstAirDate
                    tvShow.id = data.id
                    tvShow.name = data.name
                    tvShow.originalLanguage = data.originalLanguage
                    tvShow.originalName = data.originalName
                    tvShow.overview = data.overview
                    tvShow.popularity = data.popularity
                    tvShow.voteAverage = data.voteAverage
                    tvShow.posterPath = data.posterPath
                    tvShow.vote_count = data.vote_count
                    movieList.add(tvShow)
                }
                tvShowResults.postValue(movieList)
            }
        })
        return tvShowResults
    }

    override fun getDetailMovie(movieId: Int): LiveData<MovieDetailEntity> {
        val movieDetailResult = MutableLiveData<MovieDetailEntity>()
        remoteDataSource.getDetailMovie(object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onDetailMovieReceive(detailMovieResponse: MovieDetailEntity) {
                movieDetailResult.postValue(detailMovieResponse)
            }
        }, id = movieId)
        return movieDetailResult
    }

    override fun getDetailTvShow(tvId: Int): LiveData<TvShowDetailEntity> {
        val tvShowDetailResult = MutableLiveData<TvShowDetailEntity>()
        remoteDataSource.getDetailTvShow(object : RemoteDataSource.LoadDetailTvShowCallback {
            override fun onDetailTvShowReceive(detailTvShowResponse: TvShowDetailEntity) {
                tvShowDetailResult.postValue(detailTvShowResponse)
            }
        }, id = tvId)
        return tvShowDetailResult
    }

}