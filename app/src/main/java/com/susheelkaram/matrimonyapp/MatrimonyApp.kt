package com.susheelkaram.matrimonyapp

import android.app.Application
import com.susheelkaram.matrimonyapp.di.DaggerMatrimonyAppComponent
import com.susheelkaram.matrimonyapp.di.MatrimonyAppComponent
import com.susheelkaram.matrimonyapp.di.MatrimonyAppModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
class MatrimonyApp : Application() {
    companion object {
        private lateinit var appComponent: MatrimonyAppComponent
        fun getComponent() = appComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = buildComponent()
    }

    protected fun buildComponent(): MatrimonyAppComponent {
        return DaggerMatrimonyAppComponent.builder().matrimonyAppModule(MatrimonyAppModule(this))
            .build()
    }
}