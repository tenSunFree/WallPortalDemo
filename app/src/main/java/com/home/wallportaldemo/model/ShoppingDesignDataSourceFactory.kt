package com.home.wallportaldemo.model

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope

class ShoppingDesignDataSourceFactory(private val scope: CoroutineScope) :
    DataSource.Factory<Int, ShoppingDesignResponse>() {

    private val popularPostLiveDataSource: MutableLiveData<
            PageKeyedDataSource<Int, ShoppingDesignResponse>> = MutableLiveData()

    override fun create(): DataSource<Int, ShoppingDesignResponse> {
        val dataSource = ShoppingDesignPageKeyedDataSource(scope)
        popularPostLiveDataSource.postValue(dataSource)
        return dataSource
    }
}