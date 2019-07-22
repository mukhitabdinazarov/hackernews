package kz.production.mukhit.hackernews.data.remote.dao

import android.arch.persistence.room.*
import kz.production.mukhit.hackernews.data.remote.entity.CommentEntity

@Dao
interface CommentDao {

    @Query("SELECT * FROM comments WHERE parent_id =:parentId ORDER BY time DESC")
    fun getComments(parentId : Long): List<CommentEntity>

    @Query("SELECT * FROM comments WHERE parent_id =:parentId ORDER BY time DESC LIMIT :limit OFFSET :offset")
    fun getComments(parentId : Long,limit : Int, offset : Int): List<CommentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(commentEntity: CommentEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(commentEntity: CommentEntity)

}