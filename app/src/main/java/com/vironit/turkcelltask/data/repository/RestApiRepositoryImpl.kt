package com.vironit.data.repository

import com.vironit.data.api.NetworkDataSource
import com.vironit.data.api.Product
import com.vironit.turkcelltask.domain.repository.RestApiRepository
import io.reactivex.Single
import javax.inject.Inject

class RestApiRepositoryImpl @Inject constructor(val networkDataSource: NetworkDataSource) :
    RestApiRepository {

    override fun loadProductList(): Single<List<Product>> {
        return networkDataSource.getProductList()
    }

    override fun loadProductDetails(id: String): Single<Product> {
        return networkDataSource.getProductDetails(id)
    }
}