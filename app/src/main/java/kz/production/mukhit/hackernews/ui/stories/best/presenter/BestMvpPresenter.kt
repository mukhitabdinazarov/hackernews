package kz.production.mukhit.hackernews.ui.stories.best.presenter

import io.reactivex.Observable
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.ui.base.presenter.MVPPresenter
import kz.production.mukhit.hackernews.ui.stories.best.interactor.BestMvpInteractor
import kz.production.mukhit.hackernews.ui.stories.best.view.BestMvpView
import retrofit2.Response

interface BestMvpPresenter <V : BestMvpView, I : BestMvpInteractor> : MVPPresenter<V, I> {

    fun onLoadingBestStoryList()

    fun onLoadStoryItem(list : List<Long>?)

    fun getItemObservable(id: Long) : Observable<Story>

    fun cancelAllLoading()
}