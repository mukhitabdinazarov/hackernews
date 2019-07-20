package kz.production.mukhit.hackernews.data.network

import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import kz.production.mukhit.hackernews.data.model.Comment
import kz.production.mukhit.hackernews.data.model.Story
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiHelper {

    @GET(RestApiRoute.NEW_StORIES)
    fun getNewStories() : Single<Response<List<Long>>>

    @GET(RestApiRoute.TOP_STORIES)
    fun getTopStories() : Single<Response<List<Long>>>

    @GET(RestApiRoute.BEST_STORIES)
    fun getBestStories() : Single<Response<List<Long>>>

    @GET("item/{id}.json")
    fun getItem(@Path("id") itemId : Long) : Observable<Story>

    @GET("item/{id}.json")
    fun getComment(@Path("id") commentId : Long) : Observable<Comment>

    @GET("item/{id}.json")
    fun getReplies(@Path("id") commentId : Long) : Observable<Comment>


}