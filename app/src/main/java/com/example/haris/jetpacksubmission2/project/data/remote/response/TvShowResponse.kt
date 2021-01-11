package com.example.haris.jetpacksubmission2.project.data.remote.response

import com.example.haris.jetpacksubmission2.project.ui.movie.MovieEntity
import com.example.haris.jetpacksubmission2.project.ui.tvshow.TvShowEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @SerializedName("page")
    @Expose
    var page: Int? = null,

    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null,

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null,

    @SerializedName("results")
    @Expose
    var results: List<TvShowEntity>? = null
)
