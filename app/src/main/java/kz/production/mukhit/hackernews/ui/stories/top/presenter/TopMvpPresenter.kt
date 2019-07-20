package kz.production.mukhit.hackernews.ui.stories.top.presenter

import kz.production.mukhit.hackernews.ui.base.presenter.MVPPresenter
import kz.production.mukhit.hackernews.ui.stories.top.interactor.TopMvpInteractor
import kz.production.mukhit.hackernews.ui.stories.top.view.TopView

interface TopMvpPresenter  <V : TopView, I : TopMvpInteractor> : MVPPresenter<V, I> {


    fun onLoadingTopStoryList()

    fun onLoadStoryItem(list : List<Long>?)

    fun cancelAllLoading()
}