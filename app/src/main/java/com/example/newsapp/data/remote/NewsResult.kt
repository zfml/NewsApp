package com.example.newsapp.data.remote

import com.example.newsapp.domain.Article

data class NewsResult(
    val articles: List<Article>
)