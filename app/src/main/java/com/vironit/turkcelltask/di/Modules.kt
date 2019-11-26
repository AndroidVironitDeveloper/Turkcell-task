package com.vironit.turkcelltask.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vironit.data.api.ProductsApi
import com.vironit.data.db.ProductsDAO.Companion.DATABASE_NAME_PRODUCTS
import com.vironit.data.db.ProductsDatabase
import com.vironit.data.repository.RestApiRepositoryImpl
import com.vironit.turkcelltask.App
import com.vironit.turkcelltask.Constants.BASE_URL
import com.vironit.turkcelltask.Constants.IO_SCHEDULER
import com.vironit.turkcelltask.Constants.UI_SCHEDULER
import com.vironit.turkcelltask.data.repository.DatabaseRepositoryImpl
import com.vironit.turkcelltask.domain.repository.DatabaseRepository
import com.vironit.turkcelltask.domain.repository.RestApiRepository
import com.vironit.turkcelltask.domain.usecase.ProductDetailsInteractor
import com.vironit.turkcelltask.domain.usecase.ProductListInteractor
import com.vironit.turkcelltask.presentation.productdetails.ProductDetailsContact
import com.vironit.turkcelltask.presentation.productlist.ProductListContact
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: App): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    @Named(UI_SCHEDULER)
    fun provideUIScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @Singleton
    @Named(IO_SCHEDULER)
    fun provideIOScheduler(): Scheduler {
        return Schedulers.io()
    }


    @Provides
    @Singleton
    fun getRestApi(retrofit: Retrofit): ProductsApi {
        return retrofit.create<ProductsApi>(ProductsApi::class.java)
    }


    @Provides
    @Singleton
    fun getRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun getOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(httpLoggingInterceptor)
        return builder.build()
    }


    @Provides
    @Singleton
    fun getGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun getAppDatabase(context: Context): ProductsDatabase {
        return Room.databaseBuilder(
            context,
            ProductsDatabase::class.java,
            DATABASE_NAME_PRODUCTS
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}

@Module
interface RepositoryModule {
    @Binds
    @Singleton
    fun provideRestApiRepository(restApiRepository: RestApiRepositoryImpl): RestApiRepository

    @Binds
    @Singleton
    fun provideDatabaseRepository(databaseRepository: DatabaseRepositoryImpl): DatabaseRepository
}

@Module
interface InteractorModule {
    @Binds
    @Singleton
    fun provideProductListInteractor(interactor: ProductListInteractor): ProductListContact.Interactor

    @Binds
    @Singleton
    fun provideProductDetailsInteractor(interactor: ProductDetailsInteractor): ProductDetailsContact.Interactor

}




