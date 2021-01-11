package com.example.haris.jetpacksubmission2.project.ui.detailtvshow

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class TvShowDetailEntity(
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null,

    @SerializedName("episode_run_time")
    @Expose
    var episodeRunTime: List<Int?>? = null,

    @SerializedName("first_air_date")
    @Expose
    var firstAirDate: String? = null,

    @SerializedName("genres")
    @Expose
    var genres: List<Any?>? = null,

    @SerializedName("homepage")
    @Expose
    var homepage: String? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("in_production")
    @Expose
    var inProduction: Boolean? = null,

    @SerializedName("languages")
    @Expose
    var languages: List<Any?>? = null,

    @SerializedName("last_air_date")
    @Expose
    var lastAirDate: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("next_episode_to_air")
    @Expose
    var nextEpisodeToAir: Any? = null,

    @SerializedName("networks")
    @Expose
    var networks: List<Any?>? = null,

    @SerializedName("number_of_episodes")
    @Expose
    var numberOfEpisodes: Int? = null,

    @SerializedName("number_of_seasons")
    @Expose
    var numberOfSeasons: Int? = null,

    @SerializedName("origin_country")
    @Expose
    var originCountry: List<String?>? = null,

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null,

    @SerializedName("original_name")
    @Expose
    var originalName: String? = null,

    @SerializedName("overview")
    @Expose
    var overview: String? = null,

    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,

    @SerializedName("production_companies")
    @Expose
    var productionCompanies: List<Any?>? = null,

    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("type")
    @Expose
    var type: String? = null,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null,

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null
)