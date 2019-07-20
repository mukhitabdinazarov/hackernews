package kz.production.mukhit.hackernews.ui.comment.interactor

import io.reactivex.Observable
import kz.production.mukhit.hackernews.data.model.Comment
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.ui.base.interactor.MVPInteractor
import retrofit2.Response


interface CommentMvpInteractor : MVPInteractor {

    fun getStory(stotyId : Long) : Observable<Story>

    fun getComment(commentId : Long) : Observable<Comment>

    fun getReplies(commentId : Long) : Observable<Comment>
}