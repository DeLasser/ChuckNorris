package com.example.chucknorris.data.repository

import com.example.chucknorris.entities.Fact
import retrofit2.Response

interface FactsRepository {

    fun getRandomFact(category: String? = null): Response<Fact>

    fun getCategories(): Response<List<String>>
}