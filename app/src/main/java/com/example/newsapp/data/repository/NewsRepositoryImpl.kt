package com.example.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl(
    private val newsApi: NewsApi
): NewsRepository{

    override fun getAllNews(): Flow<PagingData<Article>> {
       return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                NewsPagingSource(newsApi)
            }
        ).flow
    }
}