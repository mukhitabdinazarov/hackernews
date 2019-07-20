package kz.production.mukhit.hackernews.ui.stories.last.view

import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.ui.base.view.MVPView

interface LastMvpView : MVPView {

    fun setupEndlessScrolListener()

    fun setList(list : List<Long>?)

    fun setStoryList(storyList : List<Story>?)

    fun getAllStoryInDb(limit : Int, offset : Int)

    fun insertToDb(story : Story)
}