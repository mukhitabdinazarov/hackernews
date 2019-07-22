package kz.production.mukhit.hackernews.data.remote.repository.story

import io.reactivex.Observable
import kz.production.mukhit.hackernews.data.remote.dao.StoryDao
import kz.production.mukhit.hackernews.data.remote.entity.StoryEntity
import javax.inject.Inject

class StoryRepository @Inject internal constructor(private val storyDao: StoryDao) : StoryRepo {

    override fun storyRepoIsEmpty() = Observable.fromCallable({ storyDao.getAllStory().isEmpty() })

    override fun storyRepoIsEmpty(stroyId: Long) = Observable.fromCallable({ storyDao.getStoryById(stroyId).isEmpty() })

    //get
    override fun loadStories() = Observable.fromCallable({ storyDao.getAllStory() })

    override fun loadStoryById(storyId: Long) = Observable.fromCallable({
        return@fromCallable storyDao.getStory(storyId)
    })


    override fun loadNewStoryList(limit : Int, offset : Int)
            : Observable<List<StoryEntity>> = Observable.fromCallable({ storyDao.getNewStories(limit,offset,true)})

    override fun loadTopStoryList(limit : Int, offset : Int)
            : Observable<List<StoryEntity>> = Observable.fromCallable({ storyDao.getTopStories(limit,offset,true)})

    override fun loadBestStoryList(limit : Int, offset : Int)
            : Observable<List<StoryEntity>> = Observable.fromCallable({ storyDao.getBestStories(limit,offset,true)})



    //insert
    override fun insertStories(stories: List<StoryEntity>): Observable<Boolean> {
        storyDao.insertAll(stories)
        return Observable.just(true)
    }

    override fun insert(storyEntity: StoryEntity) = Observable.fromCallable({
        storyDao.insert(storyEntity)
        return@fromCallable true
    })


    //update
    override fun update(storyEntity: StoryEntity): Observable<Boolean> {
        storyDao.update(storyEntity)
        return Observable.just(true)
    }
}