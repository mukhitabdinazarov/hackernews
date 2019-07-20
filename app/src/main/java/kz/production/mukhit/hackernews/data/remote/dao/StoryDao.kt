package kz.production.mukhit.hackernews.data.remote.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import kz.production.mukhit.hackernews.data.remote.entity.StoryEntity
import android.arch.persistence.room.Update
import android.database.sqlite.SQLiteConstraintException


@Dao
abstract class StoryDao{

    @Query("SELECT * FROM stories WHERE id =:storyId")
    abstract fun getStoryById(storyId : Long): List<StoryEntity>

    @Query("SELECT * FROM stories WHERE is_new =:isNew LIMIT :limit OFFSET :offset ")
    abstract fun getNewStories(limit : Int, offset : Int,isNew : Boolean): List<StoryEntity>

    @Query("SELECT * FROM stories WHERE is_top =:isTop LIMIT :limit OFFSET :offset ")
    abstract fun getTopStories(limit : Int, offset : Int,isTop : Boolean): List<StoryEntity>

    @Query("SELECT * FROM stories WHERE is_best =:isBest LIMIT :limit OFFSET :offset ")
    abstract fun getBestStories(limit : Int, offset : Int,isBest : Boolean): List<StoryEntity>


    @Insert(onConflict = OnConflictStrategy.FAIL)
    abstract fun insert(entity: StoryEntity)

    @Update(onConflict = OnConflictStrategy.FAIL)
    abstract fun update(entity: StoryEntity)

    open fun upsert(model: StoryEntity) {
        try {
            insert(model)
        }catch (exception: SQLiteConstraintException) {
            update(model)
        }
    }
}