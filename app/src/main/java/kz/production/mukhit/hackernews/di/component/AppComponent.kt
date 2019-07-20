package kz.production.mukhit.hackernews.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import kz.production.mukhit.hackernews.HackerApp
import kz.production.mukhit.hackernews.di.builder.ActivityBuilder
import kz.production.mukhit.hackernews.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (ActivityBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: HackerApp)

}