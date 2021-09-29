package com.example.newsapp2

import com.example.newsapp2.api.NewsApiJSON
import retrofit2.http.GET

interface APIRequest {

    @GET("/v2/everything?q=Apple&from=2021-09-27&sortBy=popularity&apiKey=85d4b730bf414d71bdc0e3331df763a2")
    suspend fun getNews() : NewsApiJSON

}