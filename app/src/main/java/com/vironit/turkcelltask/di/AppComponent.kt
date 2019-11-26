package com.vironit.turkcelltask.di

import com.vironit.turkcelltask.App
import com.vironit.turkcelltask.presentation.productdetails.ProductDetailsViewModel
import com.vironit.turkcelltask.presentation.productlist.ProductListViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, InteractorModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appContext(app: App): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)

    fun inject(viewModel: ProductListViewModel)
    fun inject(viewModel: ProductDetailsViewModel)
}