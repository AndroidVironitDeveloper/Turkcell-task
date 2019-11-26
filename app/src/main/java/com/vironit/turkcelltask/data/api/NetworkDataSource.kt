package com.vironit.data.api

import io.reactivex.Single
import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val productsApi: ProductsApi){

    fun getProductList(): Single<List<Product>>{
        return productsApi.loadProductList()
            .map { it.products }
    }

    fun getProductDetails(id: String): Single<Product>{
        return productsApi.loadProductDetails(id)
    }
}