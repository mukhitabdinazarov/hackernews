package kz.production.mukhit.hackernews.ui.comment.presenter

import io.reactivex.Observable
import kz.production.mukhit.hackernews.data.model.Comment
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.data.remote.entity.CommentEntity
import kz.production.mukhit.hackernews.ui.base.presenter.MVPPresenter
import kz.production.mukhit.hackernews.ui.comment.interactor.CommentMvpInteractor
import kz.production.mukhit.hackernews.ui.comment.view.CommentMvpView
import retrofit2.Response
import java.util.*

interface CommentMvpPresenter <V : CommentMvpView, I : CommentMvpInteractor> : MVPPresenter<V, I> {

    fun onLoadStory(storyId : Long)

    fun onLoadComments(commentIdList : List<Long>?)

    fun onLoadReplies(commentIdList : List<Long>,position : Int,commentId : Long)

    fun getItemObservable(id: Long) : Observable<Comment>

    fun getReplyItemObservable(id: Long) : Observable<Comment>

    //room
    fun getRemoteComments(parentId : Long?) : Observable<List<CommentEntity>>?

    fun getRemoteComments(parentId : Long?,limit : Int,offset : Int)

    fun getRemoteReplies(parentId : Long?,position : Int)

    fun insertComment(commentEntity: CommentEntity)
}