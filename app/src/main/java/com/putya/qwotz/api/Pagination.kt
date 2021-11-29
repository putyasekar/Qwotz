package com.putya.qwotz.api

data class Pagination(
    val currentPage: Int,
    val nextPage: Int,
    val totalPages: Int
)