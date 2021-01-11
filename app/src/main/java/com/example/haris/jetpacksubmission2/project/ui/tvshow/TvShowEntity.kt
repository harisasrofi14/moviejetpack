package com.example.haris.jetpacksubmission2.project.ui.tvshow

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvShowEntity(
    @SerializedName("original_name")
    @Expose
    var originalName: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null,

    @SerializedName("vote_count")
    @Expose
    var vote_count: Int? = null,

    @SerializedName("first_air_date")
    @Expose
    var firstAirDate: String? = null,

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null,

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null,


    @SerializedName("overview")
    @Expose
    var overview: String? = null,


    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null,

    )