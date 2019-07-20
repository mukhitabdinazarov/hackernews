package kz.production.mukhit.hackernews.ui.stories.last

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kz.production.mukhit.hackernews.ui.stories.last.view.LastFragment

@Module
internal abstract class LastFragmentProvider {
    
    @ContributesAndroidInjector(modules = [LastFragmentModule::class])
    internal abstract fun provideLastFactory(): LastFragment

}