package com.example.thenewsapp.domain.repository

import com.example.thenewsapp.data.local.db.ArticleDAO
import com.example.thenewsapp.data.network.api.RetrofitInstance
import com.example.thenewsapp.data.network.dto.Article
import javax.inject.Inject

class NewsRepository @Inject constructor(val db: ArticleDAO) {

    suspend fun getHeadlines(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getHeadlines(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.upsert(article)

    fun getFavouriteNews() = db.getAllArticles()

    suspend fun deleteArticle(article: Article) = db.deleteArticle(article)
}