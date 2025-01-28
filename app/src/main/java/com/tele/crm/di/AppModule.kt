package com.tele.crm.di

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.tele.crm.data.network.ApiService
import com.tele.crm.data.network.errorHandel.ErrorHandler
import com.tele.crm.data.network.errorHandel.ErrorHandlerImpl
import com.tele.crm.data.network.repository.CRMRepository
import com.tele.crm.data.network.repository.CRMRepositoryImpl
import com.tele.crm.utils.NetInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Enable detailed logging
        }
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://tele-crm.azurewebsites.net/") // Replace with your base URL
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideErrorHandler(gson: Gson): ErrorHandler {
        return ErrorHandlerImpl(gson)
    }

    @Singleton
    @Provides
    fun provideCRMRepository(
        apiService: ApiService,
        errorHandler: ErrorHandler
    ): CRMRepository {
        return CRMRepositoryImpl(apiService, errorHandler)
    }

    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Singleton
    @Provides
    fun provideNetInfo(connectivityManager: ConnectivityManager): NetInfo {
        return NetInfo(connectivityManager)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}
