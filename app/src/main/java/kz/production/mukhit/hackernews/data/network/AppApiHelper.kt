package kz.production.mukhit.hackernews.data.network

import io.reactivex.Observable
import io.reactivex.Single
import kz.production.mukhit.hackernews.data.model.Comment
import kz.production.mukhit.hackernews.data.model.Story
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppApiHelper @Inject constructor() :ApiHelper {

    override fun getTopStories() : Single<Response<List<Long>>> {
        return RestApi.getApi().getTopStories()
    }

    override fun getNewStories(): Single<Response<List<Long>>> {
        return RestApi.getApi().getNewStories()
    }

    override fun getBestStories(): Single<Response<List<Long>>> {
        return RestApi.getApi().getBestStories()
    }

    override fun getItem(itemId: Long): Observable<Story> {
        return RestApi.getApi().getItem(itemId)
    }

    override fun getComment(commentId: Long): Observable<Comment> {
        return RestApi.getApi().getComment(commentId)
    }

    override fun getReplies(commentId: Long): Observable<Comment> {
        return RestApi.getApi().getReplies(commentId)
    }
}