package kz.production.mukhit.hackernews.ui.stories.top.view

import kz.production.mukhit.hackernews.data.model.Story

interface StoryItemClickListener {

    fun onCommentClick(story : Story)

    fun onItemClick(webUrl : String)
}