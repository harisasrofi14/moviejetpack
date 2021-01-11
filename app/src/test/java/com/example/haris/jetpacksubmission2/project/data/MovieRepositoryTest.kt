package com.example.haris.jetpacksubmission2.project.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.haris.jetpacksubmission2.project.data.remote.RemoteDataSource
import com.example.haris.jetpacksubmission2.project.ui.detailmovie.MovieDetailEntity
import com.example.haris.jetpacksubmission2.project.ui.detailtvshow.TvShowDetailEntity
import com.example.haris.jetpacksubmission2.project.ui.movie.MovieEntity
import com.example.haris.jetpacksubmission2.project.ui.tvshow.TvShowEntity
import com.example.haris.jetpacksubmission2.project.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MovieRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepositoryTest(remote)
    private val movieResponse = ArrayList<MovieEntity>()
    private val tvShowResponse = ArrayList<TvShowEntity>()
    private val detailMovieResponse = MovieDetailEntity()
    private val detailTvShowsResponse = TvShowDetailEntity()
    private var movieId: Int = 0
    private var tvID: Int = 0

    @Before
    fun setUp() {
        movieResponse.add(
            MovieEntity(
                1993.708,
                1204,
                false,
                "/jlJ8nDhMhCYJuzOw3f52CP1W8MW.jpg",
                400160,
                false,
                "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
                "en",
                "The SpongeBob Movie: Sponge on the Run",
                "The SpongeBob Movie: Sponge on the Run",
                8.2,
                "When his best friend Gary is suddenly snatched away, SpongeBob takes Patrick on a madcap mission far beyond Bikini Bottom to save their pink-shelled pal.",
                "2020-08-14"
            )
        )
        movieResponse.add(
            MovieEntity(
                1993.708,
                1204,
                false,
                "/jlJ8nDhMhCYJuzOw3f52CP1W8MW",
                400160,
                false,
                "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
                "en",
                "The SpongeBob Movie: Sponge on the Run",
                "The SpongeBob Movie: Sponge on the Run",
                8.2,
                "When his best friend Gary is suddenly snatched away, SpongeBob takes Patrick on a madcap mission far beyond Bikini Bottom to save their pink-shelled pal.",
                "2020-08-14"
            )
        )
        tvShowResponse.add(
            TvShowEntity(
                "",
                "",
                70.0,
                90,
                "2020-10-10",
                "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
                "en",
                "When his best friend Gary is suddenly snatched away, ",
                "/jlJ8nDhMhCYJuzOw3f52CP1W8MW",
                88.0,
                123
            )
        )

        movieId = movieResponse[0].id!!
        tvID = tvShowResponse[0].id!!

        detailTvShowsResponse.name = ""
        detailTvShowsResponse.backdropPath = "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg"
        detailTvShowsResponse.episodeRunTime = null
        detailTvShowsResponse.firstAirDate = "2020-10-22"
        detailTvShowsResponse.homepage = "testing"
        detailTvShowsResponse.id = 123
        detailTvShowsResponse.inProduction = false
        detailTvShowsResponse.lastAirDate = "2020-11-20"
        detailTvShowsResponse.numberOfEpisodes = 20
        detailTvShowsResponse.numberOfSeasons = 1
        detailTvShowsResponse.originalLanguage = "en"
        detailTvShowsResponse.popularity = 90.0
        detailTvShowsResponse.voteCount = 78
        detailTvShowsResponse.voteAverage = 80.7

        detailMovieResponse.title = "The SpongeBob Movie: Sponge on the Run"
        detailMovieResponse.id =400160
        detailMovieResponse.adult = false
        detailMovieResponse.backdropPath = "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg"
        detailMovieResponse.budget = 30230231
        detailMovieResponse.homepage = ""
        detailMovieResponse.imdbId = "12233"
        detailMovieResponse.originalLanguage = "en"
        detailMovieResponse.originalTitle = "The SpongeBob Movie: Sponge on the Run"
        detailMovieResponse.overview = "When his best friend Gary is suddenly snatched away, "
        detailMovieResponse.popularity = 90.8
        detailMovieResponse.releaseDate = "2020-02-03"

    }

    @Test
    fun getAllMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMovieCallback)
                .onAllMovieReceived(movieResponse)
            null
        }.`when`(remote).getAllMovie(any())
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getAllMovie())
        verify(remote).getAllMovie(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size.toLong(), movieEntities.size.toLong())

    }

    @Test
    fun getAllTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback)
                .onAllTvShowReceived(tvShowResponse)
            null
        }.`when`(remote).getAllTvShow(any())
        val tvShowsEntities = LiveDataTestUtil.getValue(movieRepository.getAllTvShow())
        verify(remote).getAllTvShow(any())
        assertNotNull(tvShowsEntities)
        assertEquals(tvShowResponse.size.toLong(), tvShowsEntities.size.toLong())

    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailMovieCallback)
                .onDetailMovieReceive(detailMovieResponse)
            null
        }.`when`(remote).getDetailMovie(any(), eq(movieId))
        val detailMovie = LiveDataTestUtil.getValue(movieRepository.getDetailMovie(movieId))
        verify(remote).getDetailMovie(any(), eq(movieId))
        assertNotNull(detailMovie)
        assertEquals(detailMovie.title, detailMovieResponse.title)

    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailTvShowCallback)
                .onDetailTvShowReceive(detailTvShowsResponse)
            null
        }.`when`(remote).getDetailTvShow(any(), eq(tvID))
        val detailTvShow = LiveDataTestUtil.getValue(movieRepository.getDetailTvShow(tvID))
        verify(remote).getDetailTvShow(any(), eq(tvID))
        assertNotNull(detailTvShow)
        assertEquals(detailTvShow.name,detailTvShowsResponse.name)
    }
}