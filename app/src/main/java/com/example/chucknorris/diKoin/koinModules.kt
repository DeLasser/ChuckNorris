package com.example.chucknorris.diKoin

import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.example.chucknorris.Interactors.GetCategoriesInteractor
import com.example.chucknorris.Interactors.GetFactInteractor
import com.example.chucknorris.data.api.FactsApi
import com.example.chucknorris.data.repository.FactsRepository
import com.example.chucknorris.data.repository.FactsRepositoryImpl
import com.example.chucknorris.ui.categories.CategoriesViewModel
import com.example.chucknorris.ui.fact.FactViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    single<FactsApi> {
        Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
            .create(FactsApi::class.java)
    }

    single<FactsRepository> { FactsRepositoryImpl(get()) }
}

val webModule = module {

    single<Gson> {
        GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .enableComplexMapKeySerialization()
            .setVersion(1.0)
            .create()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }
}

val interactosModule = module {
    factory { GetCategoriesInteractor(get()) }
    factory { GetFactInteractor(get()) }
}

val viewModelModules = module {

    factory { (fragment: Fragment) ->
        ViewModelProvider(
            fragment,
            SavedStateViewModelFactory(fragment.activity?.application!!, fragment)
        ).get(CategoriesViewModel::class.java)
    }
    factory { (fragment: Fragment) ->
        ViewModelProvider(
            fragment,
            SavedStateViewModelFactory(fragment.activity?.application!!, fragment)
        ).get(FactViewModel::class.java)
    }
}