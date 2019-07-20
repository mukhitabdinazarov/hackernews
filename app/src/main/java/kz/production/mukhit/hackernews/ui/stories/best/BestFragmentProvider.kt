package kz.production.mukhit.hackernews.ui.stories.best

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kz.production.mukhit.hackernews.ui.stories.best.view.BestFragment

@Module
internal abstract class BestFragmentProvider {
    @ContributesAndroidInjector(modules = [BestFragmentModule::class])
    internal abstract fun provideBestFactory(): BestFragment
}