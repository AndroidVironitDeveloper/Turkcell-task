package com.vironit.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ProductEntity::class), version = 1)
abstract class ProductsDatabase : RoomDatabase() {

    abstract fun contactsDao(): ProductsDAO

    companion object {

        @Volatile private var INSTANCE: ProductsDatabase? = null

        fun getInstance(context: Context): ProductsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ProductsDatabase::class.java, "Products.db")
                .build()
    }
}