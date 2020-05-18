package com.home.wallportaldemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.home.wallportaldemo.model.ShoppingDesignResponse
import com.home.wallportaldemo.model.ShoppingDesignPageKeyedDataSource
import com.home.wallportaldemo.model.ShoppingDesignDataSourceFactory

class ShoppingDesignViewModel : ViewModel() {

    var pagedListLiveData: LiveData<PagedList<ShoppingDesignResponse>>? = null

    init {
        val dataSourceFactory = ShoppingDesignDataSourceFactory(viewModelScope)
        val config: PagedList.Config = (PagedList.Config.Builder())
            .setEnablePlaceholders(false)
            .setPageSize(ShoppingDesignPageKeyedDataSource.PAGE_SIZE)
            .build()
        pagedListLiveData = LivePagedListBuilder(dataSourceFactory, config).build()
    }
}