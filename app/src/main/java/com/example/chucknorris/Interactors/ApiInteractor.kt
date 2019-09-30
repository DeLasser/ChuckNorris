package com.example.chucknorris.Interactors

import kotlinx.coroutines.*
import retrofit2.Response

abstract class ApiInteractor<T, in Q> {

    protected abstract fun run(params: Q? = null): Response<T>

    fun invoke(
        coroutineScope: CoroutineScope = MainScope(),
        params: Q? = null,
        onResult: (T) -> Unit,
        onError: (Exception) -> Unit
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                val result = run(params)
                withContext(Dispatchers.Main) {
                    if (result.code() / 100 == 2 && result.body() != null) {
                        onResult.invoke(result.body()!!)
                    } else {
                        onError.invoke(
                            java.lang.Exception(
                                result.errorBody()?.string()
                                    ?: "${result.code()}: Unknown server error."
                            )
                        )
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError(e)
                }
            }
        }

    }
}