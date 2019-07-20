package kz.production.mukhit.hackernews.ui.stories.last.presenter

import io.reactivex.Observable
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.ui.base.presenter.MVPPresenter
import kz.production.mukhit.hackernews.ui.stories.last.interactor.LastMvpInteractor
import kz.production.mukhit.hackernews.ui.stories.last.view.LastMvpView
import retrofit2.Response

interface LastMvpPresenter <V : LastMvpView, I : LastMvpInteractor> : MVPPresenter<V, I> {

    fun onLoadingLastStoryList()

    fun onLoadStoryItem(list : List<Long>?)

    fun getItemObservable(id: Long) : Observable<Story>

}