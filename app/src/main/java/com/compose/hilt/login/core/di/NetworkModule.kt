package com.compose.hilt.login.core.di

import com.compose.hilt.login.data.network.clients.LoginClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton //es lit un singleton
    @Provides //para que hilt lo provea cuando lo llames
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL("http://demo5642715.mockable.io/api/"))
            .client(provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create()) //Convierte el json de respuesta a la data class
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    /*fun <S> create(serviceClass: Class<S>?, url: String?): S {
        return provideRetrofit(url).create(serviceClass!!)
    }*/

    @Singleton
    @Provides
    fun provideLoginClient(retrofit: Retrofit): LoginClient {
        return retrofit.create(LoginClient::class.java)
    }
}