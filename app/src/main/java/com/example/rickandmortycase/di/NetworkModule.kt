package com.example.rickandmortycase.di

import com.example.rickandmortycase.BuildConfig
import com.example.rickandmortycase.data.network.RickAndMortyCaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .baseUrl(BuildConfig.API_URL)
            .build()

    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideOkHttpInterceptor(): HttpLoggingInterceptor {

        return HttpLoggingInterceptor().apply {
            if (!BuildConfig.DEBUG) {
                this.level = HttpLoggingInterceptor.Level.NONE
            } else {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(60L, TimeUnit.SECONDS)
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .build()
    }


    @Singleton
    @Provides
    fun provideRickAndMortyCaseService(retrofit: Retrofit): RickAndMortyCaseService {
        return retrofit.create(RickAndMortyCaseService::class.java)
    }

}