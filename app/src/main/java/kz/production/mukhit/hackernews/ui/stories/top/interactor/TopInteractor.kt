package kz.production.mukhit.hackernews.ui.stories.top.interactor

import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.data.network.ApiHelper
import kz.production.mukhit.hackernews.data.remote.entity.StoryEntity
import kz.production.mukhit.hackernews.data.remote.repository.story.StoryRepo
import kz.production.mukhit.hackernews.ui.base.interactor.BaseInteractor
import retrofit2.Response
import javax.inject.Inject

class TopInteractor
@Inject internal constructor(private val storyRepoHelper: StoryRepo,apiHelper: ApiHelper) :
    BaseInteractor(apiHelper = apiHelper), TopMvpInteractor {


    override fun getStoryList(type : Int): Single<Response<List<Long>>> {
        return when(type){
            0 -> apiHelper.getNewStories()
            1 -> apiHelper.getTopStories()
            2 -> apiHelper.getBestStories()
            else -> apiHelper.getNewStories()
        }

    }

    override fun getStoryItem(itemId: Long): Observable<Story> {
        return apiHelper.getItem(itemId)
    }

    override fun storyIsEmpty(storyID: Long): Observable<Boolean> {
        return storyRepoHelper.storyRepoIsEmpty(storyID)
    }

    override fun getStoryById(storyID: Long): Observable<StoryEntity> {
        return storyRepoHelper.loadStoryById(storyID)
    }

    override fun insertStory(story: StoryEntity): Observable<Boolean> {
        return storyRepoHelper.insert(story)
    }

    override fun updateStory(story: StoryEntity): Observable<Boolean> {
        return storyRepoHelper.update(story)
    }

    override fun getRemoteStories(limit: Int, offset: Int,type : Int): Observable<List<StoryEntity>> {
        return when(type){
            0->storyRepoHelper.loadNewStoryList(limit,offset)
            1->storyRepoHelper.loadTopStoryList(limit,offset)
            2->storyRepoHelper.loadBestStoryList(limit,offset)
            else -> storyRepoHelper.loadNewStoryList(limit,offset)
        }
    }
}