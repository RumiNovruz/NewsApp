package com.example.thenewsapp.data.network.dto

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)