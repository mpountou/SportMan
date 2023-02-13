package com.intelligent.sportman.di

import com.intelligent.sportman.data.remote.api.SportApi
import com.intelligent.sportman.data.remote.repository.SportRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Moshi
    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private const val BASE_URL = "https://618d3aa7fe09aa001744060a.mockapi.io/api/"
    // Retrofit
    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    // Sport Api
    @Singleton
    @Provides
    fun provideSportApi(retrofit: Retrofit): SportApi = retrofit.create(SportApi::class.java)

    // Sport Repository
    @Singleton
    @Provides
    fun provideSportRepo(sportApi: SportApi): SportRepository = SportRepository(sportApi = sportApi)

}