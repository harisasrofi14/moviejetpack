package com.example.haris.jetpacksubmission2.project.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.haris.jetpacksubmission2.project.data.MovieRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    private val dummyMovies = ArrayList<MovieEntity>()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>


    @Before
    fun setUp() {

        viewModel = MovieViewModel(movieRepository)
        dummyMovies.add(
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
        dummyMovies.add(
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


    }

    @Test
    fun getMovie() {

        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(movieRepository.getAllMovie()).thenReturn(movies)
        val movieEntity = viewModel.getMovie().value
        verify(movieRepository).getAllMovie()
        assertNotNull(movieEntity)
        assertEquals(2, movieEntity?.size)
        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}