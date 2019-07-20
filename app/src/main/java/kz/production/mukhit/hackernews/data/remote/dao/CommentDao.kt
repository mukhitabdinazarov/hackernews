package kz.production.mukhit.hackernews.data.remote.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import kz.production.mukhit.hackernews.data.remote.entity.CommentEntity

@Dao
interface CommentDao {

    @Query("SELECT * FROM comments")
    fun getComments(): List<CommentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(commentEntity: CommentEntity)
}