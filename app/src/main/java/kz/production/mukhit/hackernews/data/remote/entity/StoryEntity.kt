package kz.production.mukhit.hackernews.data.remote.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "stories")
data class StoryEntity (

        @PrimaryKey
        @ColumnInfo(name = "id")
        var id: Long? = null,

        @ColumnInfo(name = "by")
        var author: String? = null,

        @ColumnInfo(name = "descendants")
        var commentCount: Int? = 0,

        @ColumnInfo(name = "score")
        var score: Int? = 0,

        @ColumnInfo(name = "time")
        var time: Long? = 0,

        @ColumnInfo(name = "type")
        var type: String? = null,

        @ColumnInfo(name = "title")
        var title: String? = null,

        @ColumnInfo(name = "url")
        var url: String? = null,

        @ColumnInfo(name = "is_new")
        var isNew: Boolean? = false,

        @ColumnInfo(name = "is_top")
        var isTop: Boolean? = false,

        @ColumnInfo(name = "is_best")
        var isBest: Boolean? = false

)