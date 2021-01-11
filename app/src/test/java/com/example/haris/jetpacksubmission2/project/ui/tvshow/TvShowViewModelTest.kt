package com.example.haris.jetpacksubmission2.project.ui.tvshow

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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel
    private val dummyTvShow = ArrayList<TvShowEntity>()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>


    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieRepository)
        dummyTvShow.add(
            TvShowEntity(
                "originalName",
                "name",
                1993.708,
                1204,
                "2020-08-14",
                "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
                "en",
                "When his best friend Gary is suddenly snatched away, SpongeBob takes Patrick on a madcap mission far beyond Bikini Bottom to save their pink-shelled pal.",
                "/jlJ8nDhMhCYJuzOw3f52CP1W8MW",
                8.0,
                123
            )
        )
    }

    @Test
    fun getTvShow() {
        val tv = MutableLiveData<List<TvShowEntity>>()
        tv.value = dummyTvShow

        Mockito.`when`(movieRepository.getAllTvShow()).thenReturn(tv)
        val tvShowEntity = viewModel.getTvShow().value
        Mockito.verify(movieRepository).getAllTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(1, tvShowEntity?.size)
        viewModel.getTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShow)
    }
}