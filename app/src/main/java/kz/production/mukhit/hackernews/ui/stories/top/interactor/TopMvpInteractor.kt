package kz.production.mukhit.hackernews.ui.stories.top.interactor

import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.data.remote.entity.StoryEntity
import kz.production.mukhit.hackernews.ui.base.interactor.MVPInteractor
import retrofit2.Response

interface TopMvpInteractor : MVPInteractor {

    fun getStoryList(type : Int) : Single<Response<List<Long>>>

    fun getStoryItem(itemId : Long) : Observable<Story>

    fun storyIsEmpty(storyID : Long) : Observable<Boolean>

    fun getStoryById(storyID: Long) : Observable<StoryEntity>

    fun insertStory(story : StoryEntity) : Observable<Boolean>

    fun updateStory(story : StoryEntity) : Observable<Boolean>

    fun getRemoteStories(limit : Int, offset : Int,type : Int) : Observable<List<StoryEntity>>

}