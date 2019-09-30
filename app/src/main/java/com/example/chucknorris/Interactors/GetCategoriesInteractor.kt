package com.example.chucknorris.Interactors


import com.example.chucknorris.data.repository.FactsRepository
import retrofit2.Response

class GetCategoriesInteractor(private val repository: FactsRepository) :
    ApiInteractor<List<String>, String>() {

    override fun run(params: String?): Response<List<String>> {
        return repository.getCategories()
    }
}