package com.example.chucknorris.data.api

import com.example.chucknorris.entities.Fact
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FactsApi {
    @GET("/jokes/random")
    fun getRandomFact(@Query("category") category: String? = null): Call<Fact>

    @GET("/jokes/categories")
    fun getCategories(): Call<List<String>>
}