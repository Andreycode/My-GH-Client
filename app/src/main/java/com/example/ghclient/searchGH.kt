package com.example.ghclient

import com.example.ghclient.models.GitModel
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface searchGH {
    @GET("search/repositories")
    fun searchRepos(@Query("q") keyword: String): Call<GitModel>
}