package com.example.chucknorris.data.repository

import com.example.chucknorris.data.api.FactsApi
import com.example.chucknorris.entities.Fact
import retrofit2.Response

class FactsRepositoryImpl(
    private val factsApi: FactsApi
) : FactsRepository {
    override fun getRandomFact(category: String?): Response<Fact> {
        return factsApi.getRandomFact().execute()
    }

    override fun getCategories(): Response<List<String>> {
        return factsApi.getCategories().execute()
    }
}