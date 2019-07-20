package kz.production.mukhit.hackernews.ui.comment.interactor

import io.reactivex.Observable
import kz.production.mukhit.hackernews.data.model.Comment
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.data.network.ApiHelper
import kz.production.mukhit.hackernews.ui.base.interactor.BaseInteractor
import retrofit2.Response
import javax.inject.Inject

class CommentInteractor @Inject internal constructor(apiHelper: ApiHelper) :
        BaseInteractor(apiHelper = apiHelper), CommentMvpInteractor {

    override fun getStory(stotyId: Long) = apiHelper.getItem(stotyId)

    override fun getComment(commentId: Long): Observable<Comment> {
        return apiHelper.getComment(commentId)
    }

    override fun getReplies(commentId: Long): Observable<Comment> {
        return apiHelper.getReplies(commentId)
    }
}