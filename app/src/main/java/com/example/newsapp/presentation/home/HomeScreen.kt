@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.newsapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.newsapp.domain.model.Article

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val allNews = homeViewModel.getAllNews.collectAsLazyPagingItems()


    Scaffold (
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "News"
                )
            })
        },
        content = {innerPadding ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
            ) {
                if(allNews.loadState.refresh is LoadState.Loading){
                    CircularProgressIndicator(
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(
                            count = allNews.itemCount,
                        ) {index ->
                            val article = allNews[index]
                            NewsCard(article = article)
                        }
                        item {
                            if(allNews.loadState.append is LoadState.Loading) {
                                CircularProgressIndicator()
                            }
                        }

                    }
                }
            }
        }
    )
}

@Composable
fun NewsCard(
    article: Article?
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RectangleShape,
        tonalElevation = 1.dp

        ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            AsyncImage(
                model = article?.urlToImage,
                contentDescription = "News Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp)
                ,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = article?.title ?: "",
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = article?.author ?: "",
                style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
            )

        }
    }
}