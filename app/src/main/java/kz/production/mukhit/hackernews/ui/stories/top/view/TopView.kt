package kz.production.mukhit.hackernews.ui.stories.top.view

import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.ui.base.view.MVPView

interface TopView : MVPView {

    fun setupEndlessScrolListener()

    fun setStoryList(storyList : List<Story>?)

    fun setList(list : List<Long>?)

    fun getAllStoryInDb(limit : Int, offset : Int)

    fun insertToDb(story : Story)

}