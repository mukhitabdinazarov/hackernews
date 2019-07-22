package kz.production.mukhit.hackernews.ui.stories.top.presenter

import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.data.remote.entity.StoryEntity
import kz.production.mukhit.hackernews.ui.base.presenter.MVPPresenter
import kz.production.mukhit.hackernews.ui.stories.top.interactor.TopMvpInteractor
import kz.production.mukhit.hackernews.ui.stories.top.view.TopView
import java.util.*

interface TopMvpPresenter  <V : TopView, I : TopMvpInteractor> : MVPPresenter<V, I> {


    fun onLoadingStoryList(type : Int)

    fun onLoadStoryItem(list : List<Long>?)

    fun cancelAllLoading()

    fun storyIsEmpty(story : StoryEntity)

    fun loadStory(story : StoryEntity?)

    fun saveStory(story : StoryEntity)

    fun updateStory(story : StoryEntity)

    fun loadRemoteStoryList(limit : Int,offset : Int,type : Int)
}