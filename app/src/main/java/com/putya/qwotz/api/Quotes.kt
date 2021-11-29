package com.putya.qwotz.api


data class Quotes(
    val data: List<Data>,
    val message: String,
    val pagination: Pagination,
    val statusCode: Int,
    val totalQuotes: Int
)