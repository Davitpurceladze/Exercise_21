package com.example.exercise_21.di

import android.content.Context
import androidx.room.Room
import com.example.exercise_21.BuildConfig
import com.example.exercise_21.data.local.dao.ClothesDao
import com.example.exercise_21.data.local.database.AppDatabase
import com.example.exercise_21.data.remote.common.HandleResponse
import com.example.exercise_21.data.remote.service.ClothesService
import com.example.exercise_21.observeconnectivity.ConnectivityObserver
import com.example.exercise_21.observeconnectivity.NetworkConnectivityObserver
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        val client = OkHttpClient.Builder()
        if(BuildConfig.DEBUG){
            client.addInterceptor(httpLoggingInterceptor)
        }
        return client.build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): MoshiConverterFactory {
        return MoshiConverterFactory.create(
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        )
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideClothesService(retrofit: Retrofit): ClothesService {
        return retrofit.create(ClothesService::class.java)
    }

    @Provides
    @Singleton
    fun provideHandleResponse(): HandleResponse {
        return HandleResponse()
    }

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "clothes-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideClothesDao(appDatabase: AppDatabase): ClothesDao {
        return appDatabase.clothesDao()
    }

    @Provides
    @Singleton
    fun provideNetworkStatus(@ApplicationContext context: Context): NetworkConnectivityObserver {
        return NetworkConnectivityObserver(context)
    }
}

