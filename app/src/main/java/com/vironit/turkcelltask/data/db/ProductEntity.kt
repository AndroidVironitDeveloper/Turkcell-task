package com.vironit.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vironit.data.db.ProductsDAO.Companion.DATABASE_NAME_PRODUCTS
import com.vironit.data.db.ProductsDAO.Companion.ID_FIELD
import com.vironit.data.db.ProductsDAO.Companion.IMAGE_FIELD
import com.vironit.data.db.ProductsDAO.Companion.NAME_FIELD
import com.vironit.data.db.ProductsDAO.Companion.PRICE_FIELD


@Entity(tableName = DATABASE_NAME_PRODUCTS)
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = ID_FIELD)
    val id: String,
    @ColumnInfo(name = NAME_FIELD) val name: String,
    @ColumnInfo(name = PRICE_FIELD) val price: Int,
    @ColumnInfo(name = IMAGE_FIELD) val image: String
)

