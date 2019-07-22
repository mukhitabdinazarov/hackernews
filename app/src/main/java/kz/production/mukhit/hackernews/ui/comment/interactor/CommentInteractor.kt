package kz.production.mukhit.hackernews.ui.comment.interactor

import io.reactivex.Observable
import kz.production.mukhit.hackernews.data.model.Comment
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.data.network.ApiHelper
import kz.production.mukhit.hackernews.data.remote.entity.CommentEntity
import kz.production.mukhit.hackernews.data.remote.repository.comment.CommentRepo
import kz.production.mukhit.hackernews.ui.base.interactor.BaseInteractor
import retrofit2.Response
import javax.inject.Inject

class CommentInteractor @Inject internal constructor(private val commentRepoHelper: CommentRepo, apiHelper: ApiHelper) :
        BaseInteractor(apiHelper = apiHelper), CommentMvpInteractor {

    override fun getStory(stotyId: Long) = apiHelper.getItem(stotyId)

    override fun getComment(commentId: Long): Observable<Comment> {
        return apiHelper.getComment(commentId)
    }

    override fun getReplies(commentId: Long): Observable<Comment> {
        return apiHelper.getReplies(commentId)
    }

    //room
    override fun getRemoteComments(parentId: Long): Observable<List<CommentEntity>> {
        return commentRepoHelper.loadComments(parentId)
    }

    override fun getRemoteComments(parentId : Long,limit : Int, offset : Int): Observable<List<CommentEntity>> {
        return commentRepoHelper.loadComments(parentId,limit, offset)
    }



    override fun insertToDb(commentEntity : CommentEntity): Observable<Boolean> {
        return commentRepoHelper.insert(commentEntity)
    }
}