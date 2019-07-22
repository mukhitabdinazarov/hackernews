package kz.production.mukhit.hackernews.data.remote.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentEntity (

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "by")
    var author: String? = null,

    @ColumnInfo(name = "time")
    var time: Long? = 0,

    @ColumnInfo(name = "type")
    var type: String? = null,

    @ColumnInfo(name = "text")
    var text: String? = null,

    @ColumnInfo(name = "parent_id")
    var parentId: Long? = null,

    @ColumnInfo(name = "reply_count")
    var replyCounts: Int? = null

)