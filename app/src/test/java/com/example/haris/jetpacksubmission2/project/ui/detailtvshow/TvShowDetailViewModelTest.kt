package com.example.haris.jetpacksubmission2.project.ui.detailtvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.haris.jetpacksubmission2.project.data.MovieRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowDetailViewModelTest {

    private lateinit var viewModel: TvShowDetailViewModel
    private var dummyDetailTvShow = TvShowDetailEntity()
    private var tvId: Int = 0

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var detailTvObserver: Observer<TvShowDetailEntity>

    @Before
    fun setUp() {

        viewModel = TvShowDetailViewModel(movieRepository)
        dummyDetailTvShow = TvShowDetailEntity(
            "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
            null,
            "2020-10-10",
            null,
            "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
            123,
            null,
            null,
            "2020-11-11",
            "dummy",
            "2020-11-20",
            null,
            20,
            2,
            null,
            "en",
            "dummy",
            "When his best friend Gary is suddenly snatched away,",
            80.9,
            "/jlJ8nDhMhCYJuzOw3f52CP1W8MW.jpg",
            null,
            "finish",
            "type",
            80.7,
            176
        )
        tvId = dummyDetailTvShow.id ?: 0
    }

    @Test
    fun getDetailTvShow() {
        val tvShow = MutableLiveData<TvShowDetailEntity>()
        tvShow.value = dummyDetailTvShow

        Mockito.`when`(movieRepository.getDetailTvShow(tvId)).thenReturn(tvShow)

        val detailTvShowEntity = viewModel.getDetailTvShow(tvId)
        verify(movieRepository).getDetailTvShow(tvId)
        Assert.assertNotNull(detailTvShowEntity)
        Assert.assertEquals(dummyDetailTvShow.id, tvShow.value?.id)
        Assert.assertEquals(dummyDetailTvShow.name, tvShow.value?.name)

        viewModel.getDetailTvShow(tvId).observeForever(detailTvObserver)
        verify(detailTvObserver).onChanged(dummyDetailTvShow)
    }
}