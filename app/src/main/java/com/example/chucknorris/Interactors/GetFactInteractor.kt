package com.example.chucknorris.Interactors


import com.example.chucknorris.data.repository.FactsRepository
import com.example.chucknorris.entities.Fact
import retrofit2.Response

class GetFactInteractor(private val repository: FactsRepository) :
    ApiInteractor<Fact, String>() {

    override fun run(params: String?): Response<Fact> {
        return repository.getRandomFact()
    }
}