package com.example.haris.jetpacksubmission2.project.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.haris.jetpacksubmission2.R
import com.example.haris.jetpacksubmission2.project.ui.movie.MovieEntity
import com.example.haris.jetpacksubmission2.project.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val dummyMovies = ArrayList<MovieEntity>()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
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

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)


    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.tv_detail_title)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rating_bar)).check(ViewAssertions.matches(isDisplayed()))

    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }
}