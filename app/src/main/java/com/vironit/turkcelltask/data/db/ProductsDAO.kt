package com.vironit.data.db

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ProductsDAO {

    @Query("SELECT * FROM $DATABASE_NAME_PRODUCTS ORDER BY $ID_FIELD ASC")
    fun getProducts(): Flowable<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContacts(contacts: List<ProductEntity>)

    companion object {
        internal const val DATABASE_NAME_PRODUCTS = "Products"
        internal const val ID_FIELD = "id"
        internal const val NAME_FIELD = "name"
        internal const val IMAGE_FIELD = "image"
        internal const val PRICE_FIELD = "price"
    }
}