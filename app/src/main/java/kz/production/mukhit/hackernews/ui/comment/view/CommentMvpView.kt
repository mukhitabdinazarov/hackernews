package kz.production.mukhit.hackernews.ui.comment.view

import kz.production.mukhit.hackernews.data.model.Comment
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.ui.base.view.MVPView
import java.text.FieldPosition


interface CommentMvpView : MVPView {

    fun setUp()

    fun setupEndlessScrolListener()

    fun setStory(story: Story)

    fun onSetComment(commentList : List<Comment>?)

    fun onSetCommentReplies(replyList : List<Comment>?,position: Int,commentId : Long)
}