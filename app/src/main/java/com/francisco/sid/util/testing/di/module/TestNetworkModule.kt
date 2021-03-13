package com.francisco.sid.util.testing.di.module

import com.francisco.sid.data.network.EventApi
import com.francisco.sid.data.repository.EventsRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.mockk.mockk
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class TestNetworkModule {

    @Provides
    @Singleton
    fun provideEventsRepository(api: EventApi): EventsRepository = mockk()

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): EventApi = mockk()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        mockk()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = mockk()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = mockk()

    @Provides
    @Singleton
    fun provideHttLoggingIntereceptor(): HttpLoggingInterceptor = mockk()
}