package com.susheelkaram.matrimonyapp.di

import android.app.Application
import com.susheelkaram.matrimonyapp.data.local.db.AppDatabase
import com.susheelkaram.matrimonyapp.data.local.db.MatchesDao
import com.susheelkaram.matrimonyapp.data.remote.Api
import com.susheelkaram.matrimonyapp.data.remote.MatchesService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
@Module
class MatrimonyAppModule(val application: Application) {
    @Singleton
    @Provides
    fun provideDb(): AppDatabase {
        return AppDatabase.getInstance(application)
    }

    @Singleton
    @Provides
    fun provideMatchesDao(db: AppDatabase): MatchesDao {
        return db.matchesDao()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(): Api {
        return Api();
    }

    @Singleton
    @Provides
    fun profileMatchesService(api: Api): MatchesService {
        return api.matchesService;
    }
}