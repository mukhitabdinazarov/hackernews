package kz.production.mukhit.hackernews.ui.stories.top

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kz.production.mukhit.hackernews.ui.stories.top.view.TopFragment

@Module
internal abstract class TopFragmentProvider {

    @ContributesAndroidInjector(modules = [TopFragmentModule::class])
    internal abstract fun provideTopFactory(): TopFragment


}