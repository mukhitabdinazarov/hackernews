package kz.production.mukhit.hackernews.ui.stories.top

import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import kz.production.mukhit.hackernews.ui.stories.top.interactor.TopInteractor
import kz.production.mukhit.hackernews.ui.stories.top.interactor.TopMvpInteractor
import kz.production.mukhit.hackernews.ui.stories.top.presenter.TopMvpPresenter
import kz.production.mukhit.hackernews.ui.stories.top.presenter.TopPresenter
import kz.production.mukhit.hackernews.ui.stories.top.view.StoryAdapter
import kz.production.mukhit.hackernews.ui.stories.top.view.TopFragment
import kz.production.mukhit.hackernews.ui.stories.top.view.TopView

@Module
class TopFragmentModule {

    @Provides
    internal fun provideTopInteractor(interactor: TopInteractor): TopMvpInteractor = interactor

    @Provides
    internal fun provideTopPresenter(presenter: TopPresenter<TopView, TopMvpInteractor>)
            : TopMvpPresenter<TopView, TopMvpInteractor> = presenter

    @Provides
    internal fun provideStoryAdapter(): StoryAdapter = StoryAdapter()

    @Provides
    internal fun provideLinearLayoutManager(fragment: TopFragment): LinearLayoutManager =
        LinearLayoutManager(fragment.context)
}