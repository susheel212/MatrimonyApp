package com.susheelkaram.matrimonyapp.di

import com.susheelkaram.matrimonyapp.MatrimonyApp
import com.susheelkaram.matrimonyapp.ui.matches.MatchesActivity
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
@Singleton
@Component(
    modules = [MatrimonyAppModule::class]
)
interface MatrimonyAppComponent{
    fun inject(matchesActivity: MatchesActivity)
}