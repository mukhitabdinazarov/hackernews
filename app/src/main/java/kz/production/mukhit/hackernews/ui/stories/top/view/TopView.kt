package kz.production.mukhit.hackernews.ui.stories.top.view

import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.data.remote.entity.StoryEntity
import kz.production.mukhit.hackernews.ui.base.view.MVPView

interface TopView : MVPView {

    fun setupEndlessScrolListener()

    fun setList(list : List<Long>?)

    fun setStoryList(storyList : List<Story>?)

    fun getAllStoryInDb(limit : Int, offset : Int)

    fun setAllStoryInDb(storyList : List<StoryEntity>)
    fun insertToDb(story : Story)

}