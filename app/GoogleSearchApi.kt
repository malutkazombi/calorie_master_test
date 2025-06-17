package com.example.myapplication

import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleSearchApi {
    @GET("customsearch/v1")
    suspend fun search(
        @Query("key") apiKey: String,
        @Query("cx") searchEngineId: String,
        @Query("q") query: String
    ): GoogleSearchResponse
}

data class GoogleSearchResponse(
    val items: List<SearchItem>?
)

data class SearchItem(
    val title: String,
    val link: String,
    val snippet: String
)
