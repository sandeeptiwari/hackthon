package com.solvathon.di

import android.util.Log
import com.solvathon.core.Session
import com.solvathon.domain.URLFactory
import com.solvathon.domain.datasource.PolicyLiveDataSource
import com.solvathon.domain.service.PolicyService
import com.visbiliti.exception.AuthenticationException
import com.visbiliti.exception.ServerError
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetModule {

    @Singleton
    @Provides
    internal fun provideClient(
        @Named("header") headerInterceptor: Interceptor,
        @Named("pre_validation") networkInterceptor: Interceptor
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(networkInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        Log.d("tag", "URLFactory.provideHttpUrl()" + URLFactory.provideHttpUrl())
        return Retrofit.Builder()
            .baseUrl(URLFactory.provideHttpUrl())
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePolicyService(retrofit: Retrofit): PolicyService {
        return retrofit.create(PolicyService::class.java)
    }

    @Provides
    @Singleton
    @Named("header")
    internal fun provideHeaderInterceptor(session: Session): Interceptor {

        return Interceptor { chain ->
            Log.d("tag","session.userSession" +session.userSession)
            if (session.userSession.isNotEmpty()) {
                val build = chain.request().newBuilder()
                    .addHeader(Session.API_KEY, session.apiKey.replace("\u0000",""))
                    .addHeader(Session.APP, Session.APP_VALUE.replace("\u0000",""))
                    .addHeader(Session.USER_SESSION, /*"Bearer " +*/ session.userSession.replace("\u0000",""))
                    /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                          if (Resources.getSystem().configuration.locales[0].language == "ar") "ar"
                          else "en"
                      } else {
                          if (Resources.getSystem().configuration.locale.language == "ar") "ar"
                          else "en"
                      }*/
                    .build()
                chain.proceed(build)
            } else {
                val build = chain.request().newBuilder()
                    .addHeader(Session.API_KEY, session.apiKey.replace("\u0000",""))
                    .addHeader(Session.APP, Session.APP_VALUE.replace("\u0000",""))
                    .addHeader(Session.USER_SESSION, /*"Bearer " +*/ session.userSession.replace("\u0000",""))

                    /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        if (Resources.getSystem().configuration.locales[0].language == "ar") "ar"
                        else "en"
                    } else {
                        if (Resources.getSystem().configuration.locale.language == "ar") "ar"
                        else "en"
                    }*/
                    .build()
                chain.proceed(build)
            }
        }
    }

    @Provides
    @Singleton
    @Named("pre_validation")
    internal fun provideNetworkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val code = response.code

            if (code >= 500)
                throw ServerError(
                    "Unknown server error",
                    response.body!!.string()
                )
            else if (code == 401 || code == 403)
                throw AuthenticationException()
            response
        }
    }

}