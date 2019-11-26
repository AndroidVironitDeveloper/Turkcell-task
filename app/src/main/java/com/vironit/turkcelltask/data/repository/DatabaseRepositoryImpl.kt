package com.vironit.turkcelltask.data.repository

import com.vironit.data.api.Product
import com.vironit.data.db.ProductEntity
import com.vironit.data.db.ProductsDatabase
import com.vironit.turkcelltask.domain.repository.DatabaseRepository
import io.reactivex.Flowable
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(val database: ProductsDatabase) :
    DatabaseRepository {

    override fun saveProductList(list: List<Product>) {
        database.contactsDao()
            .insertContacts(list.map { ProductEntity(it.id, it.name, it.price, it.imageUrl) })
    }

    override fun getCachedProductList(): Flowable<List<Product>> {
        return database.contactsDao().getProducts()
            .map { list -> list.map { Product(it.id, it.name, it.price, it.image, null) } }
    }
}