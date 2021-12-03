package com.jeckso.architecture_test.di.network

import com.jeckso.reddit.data.network.rest.services.PostsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServicesModule {

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): PostsService {
        return retrofit.create(PostsService::class.java)
    }

}