package com.example.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.newsapp.data.remote.NewsApi
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi
) {
    fun getNews() = Pager(
        config = PagingConfig(
            pageSize = 10
        ),
        pagingSourceFactory = {
            NewsPagingSource(newsApi)
        }
    ).flow
}