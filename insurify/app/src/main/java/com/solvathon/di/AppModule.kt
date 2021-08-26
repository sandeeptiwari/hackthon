package com.solvathon.di

import android.content.Context
import android.content.SharedPreferences
import com.solvathon.core.AppPreferences
import com.solvathon.core.AppSession
import com.solvathon.core.Common
import com.solvathon.core.Session
import com.solvathon.domain.service.PolicyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context) =
        context.getSharedPreferences(
            Common.SHARED_PREF_NAME, Context.MODE_PRIVATE
        )

    @Singleton
    @Provides
    fun provideSessionManager(preferences: SharedPreferences) =
        AppPreferences(preferences)

    @Singleton
    @Provides
    fun provideAppSession(appPreferences: AppPreferences,
                          @ApplicationContext context: Context) : Session {
      return AppSession(appPreferences, context, "")
    }
}