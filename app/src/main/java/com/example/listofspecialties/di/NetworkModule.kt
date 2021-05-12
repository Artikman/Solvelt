package com.example.listofspecialties.di

import android.content.Context
import com.example.listofspecialties.data.repository.WorkerDetailsRepositoryImpl
import com.example.listofspecialties.data.repository.WorkerRepositoryImpl
import com.example.listofspecialties.data.repository.WorkerSpecialityRepositoryImpl
import com.example.listofspecialties.data.source.local.AppDatabase
import com.example.listofspecialties.data.source.remote.RetrofitService
import com.example.listofspecialties.domain.repository.WorkerDetailsRepository
import com.example.listofspecialties.domain.repository.WorkerRepository
import com.example.listofspecialties.domain.repository.WorkerSpecialityRepository
import com.example.listofspecialties.util.Constants.BASE_URL
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val mCache = Cache(context.cacheDir, cacheSize)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .cache(mCache)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addInterceptor { chain ->
                var request = chain.request()
                request = request.newBuilder()
                    .header("Cache-Control", "public, max-age=" + 5).build()
                chain.proceed(request)
            }
        return client.build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun provideWorkerRepository(
        appDatabase: AppDatabase
    ): WorkerRepository {
        return WorkerRepositoryImpl(appDatabase)
    }

    @Singleton
    @Provides
    fun provideWorkerDetailsRepository(
        appDatabase: AppDatabase
    ): WorkerDetailsRepository {
        return WorkerDetailsRepositoryImpl(appDatabase)
    }

    @Singleton
    @Provides
    fun provideWorkerSpecialityRepository(
        appDatabase: AppDatabase,
        retrofitService: RetrofitService
    ): WorkerSpecialityRepository {
        return WorkerSpecialityRepositoryImpl(appDatabase, retrofitService)
    }
}