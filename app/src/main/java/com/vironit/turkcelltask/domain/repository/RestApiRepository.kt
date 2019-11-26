package com.vironit.turkcelltask.domain.repository

import com.vironit.data.api.Product
import io.reactivex.Single

interface RestApiRepository {

    fun loadProductList(): Single<List<Product>>
    fun loadProductDetails(id: String): Single<Product>
}