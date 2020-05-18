package com.home.wallportaldemo.model

import android.util.Log
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.lang.Exception

class ShoppingDesignPageKeyedDataSource(private val scope: CoroutineScope) :
    PageKeyedDataSource<Int, ShoppingDesignResponse>() {

    companion object {
        const val FIRST_PAGE = 1
        const val PAGE_SIZE = 15
    }

    var api: Api = RetrofitService.createService(Api::class.java)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ShoppingDesignResponse>
    ) {
        scope.launch {
            try {
                val response =
                    api.getShoppingDesignResponse(FIRST_PAGE)
                when {
                    response.isSuccessful -> {
                        callback.onResult(
                            response.body()!!, null,
                            FIRST_PAGE + 1
                        )
                    }
                }
            } catch (exception: Exception) {
                Log.e("repository->Posts", "1" + exception.message)
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ShoppingDesignResponse>
    ) {
        scope.launch {
            try {
                val response =
                    api.getShoppingDesignResponse(params.key)
                when {
                    response.isSuccessful -> {
                        val key: Int? = if (response.body()?.isNotEmpty()!!) {
                            params.key + 1
                        } else {
                            null
                        }
                        callback.onResult(response.body()!!, key)
                    }
                }
            } catch (exception: Exception) {
                Log.e("repository->Popular", "1" + exception.message)
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ShoppingDesignResponse>
    ) {
        scope.launch {
            try {
                val response =
                    api.getShoppingDesignResponse(params.key)
                val key: Int? = if (params.key > 1) {
                    params.key - 1
                } else {
                    null
                }
                when {
                    response.isSuccessful -> {
                        callback.onResult(response.body()!!, key)
                    }
                }
            } catch (exception: Exception) {
                Log.e("repository->Popular", "1" + exception.message)
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}