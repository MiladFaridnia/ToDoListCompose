package com.faridnia.todolistcompose.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.faridnia.todolistcompose.Constants
import com.faridnia.todolistcompose.data.remote.ToDoListApi
import com.faridnia.todolistcompose.data.repository.LoginRepositoryImpl
import com.faridnia.todolistcompose.data.repository.ToDoRepositoryImpl
import com.faridnia.todolistcompose.domain.repository.LoginRepository
import com.faridnia.todolistcompose.domain.repository.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApi(@ApplicationContext context: Context): ToDoListApi {
        val client = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(ChuckerInterceptor(context))
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ToDoListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(api: ToDoListApi): LoginRepository {
        return LoginRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideToDoRepositoryImpl(api: ToDoListApi): ToDoRepository {
        return ToDoRepositoryImpl(api)
    }

}