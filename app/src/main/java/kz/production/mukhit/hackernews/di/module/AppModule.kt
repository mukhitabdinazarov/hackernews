package kz.production.mukhit.hackernews.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import kz.production.mukhit.hackernews.data.network.ApiHelper
import kz.production.mukhit.hackernews.data.network.AppApiHelper
import kz.production.mukhit.hackernews.data.remote.AppDatabase
import kz.production.mukhit.hackernews.data.remote.repository.comment.CommentRepo
import kz.production.mukhit.hackernews.data.remote.repository.comment.CommentRepository
import kz.production.mukhit.hackernews.data.remote.repository.story.StoryRepo
import kz.production.mukhit.hackernews.data.remote.repository.story.StoryRepository
import kz.production.mukhit.hackernews.utils.AppConstants
import kz.production.mukhit.hackernews.utils.SchedulerProvider
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper


    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.APP_DB_NAME).build()

    @Provides
    @Singleton
    internal fun provideStoryRepoHelper(appDatabase: AppDatabase): StoryRepo = StoryRepository(appDatabase.storyDao())

    @Provides
    @Singleton
    internal fun provideCommentRepoHelper(appDatabase: AppDatabase): CommentRepo = CommentRepository(appDatabase.commentDao())

}