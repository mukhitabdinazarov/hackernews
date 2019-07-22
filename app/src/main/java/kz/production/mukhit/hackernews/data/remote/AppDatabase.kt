package kz.production.mukhit.hackernews.data.remote

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import kz.production.mukhit.hackernews.data.remote.dao.CommentDao
import kz.production.mukhit.hackernews.data.remote.dao.StoryDao
import kz.production.mukhit.hackernews.data.remote.entity.CommentEntity
import kz.production.mukhit.hackernews.data.remote.entity.StoryEntity

@Database(entities = arrayOf(
    StoryEntity::class,
    CommentEntity::class),
    version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun storyDao(): StoryDao

    abstract fun commentDao(): CommentDao
}