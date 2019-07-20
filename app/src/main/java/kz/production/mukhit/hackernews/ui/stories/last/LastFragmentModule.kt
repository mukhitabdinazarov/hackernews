package kz.production.mukhit.hackernews.ui.stories.last

import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import kz.production.mukhit.hackernews.ui.stories.last.interactor.LastInteractor
import kz.production.mukhit.hackernews.ui.stories.last.interactor.LastMvpInteractor
import kz.production.mukhit.hackernews.ui.stories.last.presenter.LastMvpPresenter
import kz.production.mukhit.hackernews.ui.stories.last.presenter.LastPresenter
import kz.production.mukhit.hackernews.ui.stories.last.view.LastFragment
import kz.production.mukhit.hackernews.ui.stories.last.view.LastMvpView
import kz.production.mukhit.hackernews.ui.stories.top.view.StoryAdapter

@Module
class LastFragmentModule {
    @Provides
    internal fun provideLastInteractor(interactor: LastInteractor): LastMvpInteractor = interactor

    @Provides
    internal fun provideLastPresenter(presenter: LastPresenter<LastMvpView, LastMvpInteractor>)
            : LastMvpPresenter<LastMvpView, LastMvpInteractor> = presenter

    @Provides
    internal fun provideStoryAdapter(): StoryAdapter = StoryAdapter()

    @Provides
    internal fun provideLinearLayoutManager(fragment: LastFragment): LinearLayoutManager =
        LinearLayoutManager(fragment.context)
}