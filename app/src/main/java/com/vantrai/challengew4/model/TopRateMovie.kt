package com.vantrai.challengew4.model

data class TopRateMovie(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: ArrayList<Movie>
) {
}