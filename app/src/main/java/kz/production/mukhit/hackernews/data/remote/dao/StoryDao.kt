package kz.production.mukhit.hackernews.data.remote.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import kz.production.mukhit.hackernews.data.remote.entity.StoryEntity
import android.arch.persistence.room.Update
import android.database.sqlite.SQLiteConstraintException


@Dao
interface StoryDao{

    @Query("SELECT * FROM stories")
    fun getAllStory() : List<StoryEntity>

    @Query("SELECT * FROM stories WHERE id =:storyId")
    fun getStoryById(storyId : Long): List<StoryEntity>

    @Query("SELECT * FROM stories WHERE id =:storyId")
    fun getStory(storyId : Long): StoryEntity

    @Query("SELECT * FROM stories WHERE is_new =:isNew ORDER BY time DESC LIMIT :limit OFFSET :offset ")
    fun getNewStories(limit : Int, offset : Int,isNew : Boolean): List<StoryEntity>

    @Query("SELECT * FROM stories WHERE is_top =:isTop ORDER BY time DESC LIMIT :limit OFFSET :offset ")
    fun getTopStories(limit : Int, offset : Int,isTop : Boolean): List<StoryEntity>

    @Query("SELECT * FROM stories WHERE is_new =:isNew  AND is_top =:isTop AND is_best =:isBest LIMIT :limit OFFSET :offset ")
    fun getStories(limit : Int, offset : Int,isNew : Boolean, isTop : Boolean, isBest : Boolean): List<StoryEntity>


    @Query("SELECT * FROM stories WHERE is_best =:isBest ORDER BY time DESC LIMIT :limit OFFSET :offset ")
    fun getBestStories(limit : Int, offset : Int,isBest : Boolean): List<StoryEntity>


    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(entity: StoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stories: List<StoryEntity>)

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun update(entity: StoryEntity)

}