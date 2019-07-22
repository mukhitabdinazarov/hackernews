package kz.production.mukhit.hackernews.data.remote.repository.story

import io.reactivex.Observable
import kz.production.mukhit.hackernews.data.remote.entity.StoryEntity

interface StoryRepo {

    fun storyRepoIsEmpty(): Observable<Boolean>

    fun storyRepoIsEmpty(stroyId : Long): Observable<Boolean>

    fun loadStories(): Observable<List<StoryEntity>>

    fun loadStoryById(storyId : Long) : Observable<StoryEntity>

    fun loadNewStoryList(limit : Int,offset : Int): Observable<List<StoryEntity>>
    fun loadTopStoryList(limit : Int,offset : Int): Observable<List<StoryEntity>>
    fun loadBestStoryList(limit : Int,offset : Int): Observable<List<StoryEntity>>

    fun insertStories(stories : List<StoryEntity>): Observable<Boolean>

    fun insert(storyEntity : StoryEntity): Observable<Boolean>

    fun update(storyEntity : StoryEntity): Observable<Boolean>




}