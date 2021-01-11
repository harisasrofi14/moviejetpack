package com.example.haris.jetpacksubmission2.project.ui.detailmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.haris.jetpacksubmission2.project.data.MovieRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private var dummyDetailMovie = MovieDetailEntity()
    private var movieId: Int = 0

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var detailMovieObserver: Observer<MovieDetailEntity>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieRepository)
        dummyDetailMovie = MovieDetailEntity(
            false,
            "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
            "",
            100000,
            "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
            102002,
            "wqw",
            "en",
            "dummy",
            "When his best friend Gary is suddenly snatched away,",
            100.0,
            "/jlJ8nDhMhCYJuzOw3f52CP1W8MW.jpg",
            "2020-10-10",
            100009,
            90,
            "release",
            "dummy",
            "dummy",
            false,
            80.0,
            190
        )
        movieId = dummyDetailMovie.id ?: 0
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<MovieDetailEntity>()
        movie.value = dummyDetailMovie

        `when`(movieRepository.getDetailMovie(movieId)).thenReturn(movie)

        val detailMovieEntity = viewModel.getDetailMovie(movieId)
        verify(movieRepository).getDetailMovie(movieId)
        assertNotNull(detailMovieEntity)
        assertEquals(dummyDetailMovie.id, movie.value?.id)
        assertEquals(dummyDetailMovie.title, movie.value?.title)

        viewModel.getDetailMovie(movieId).observeForever(detailMovieObserver)
        verify(detailMovieObserver).onChanged(dummyDetailMovie)
    }
}