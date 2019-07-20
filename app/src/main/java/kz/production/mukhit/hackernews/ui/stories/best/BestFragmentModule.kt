package kz.production.mukhit.hackernews.ui.stories.best

import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import kz.production.mukhit.hackernews.ui.stories.best.interactor.BestInteractor
import kz.production.mukhit.hackernews.ui.stories.best.interactor.BestMvpInteractor
import kz.production.mukhit.hackernews.ui.stories.best.presenter.BestMvpPresenter
import kz.production.mukhit.hackernews.ui.stories.best.presenter.BestPresenter
import kz.production.mukhit.hackernews.ui.stories.best.view.BestFragment
import kz.production.mukhit.hackernews.ui.stories.best.view.BestMvpView
import kz.production.mukhit.hackernews.ui.stories.top.view.StoryAdapter

@Module
class BestFragmentModule {

    @Provides
    internal fun provideBestInteractor(interactor: BestInteractor): BestMvpInteractor = interactor

    @Provides
    internal fun provideBestPresenter(presenter: BestPresenter<BestMvpView, BestMvpInteractor>)
            : BestMvpPresenter<BestMvpView, BestMvpInteractor> = presenter

    @Provides
    internal fun provideStoryAdapter(): StoryAdapter = StoryAdapter()

    @Provides
    internal fun provideLinearLayoutManager(fragment: BestFragment): LinearLayoutManager =
        LinearLayoutManager(fragment.context)
}