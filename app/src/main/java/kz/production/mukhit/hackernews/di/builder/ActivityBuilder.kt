package kz.production.mukhit.hackernews.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kz.production.mukhit.hackernews.ui.comment.CommentActivityModule
import kz.production.mukhit.hackernews.ui.comment.view.CommentActivity
import kz.production.mukhit.hackernews.ui.main.MainModule
import kz.production.mukhit.hackernews.ui.main.view.MainActivity
import kz.production.mukhit.hackernews.ui.stories.best.BestFragmentProvider
import kz.production.mukhit.hackernews.ui.stories.last.LastFragmentProvider
import kz.production.mukhit.hackernews.ui.stories.top.TopFragmentProvider

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [
        (MainModule::class),
        (TopFragmentProvider::class),
        (LastFragmentProvider::class),
        (BestFragmentProvider::class)])
    abstract fun bindMainActivity(): MainActivity


    @ContributesAndroidInjector(modules = [
        (CommentActivityModule::class)])
    abstract fun bindCommentActivity() : CommentActivity
}