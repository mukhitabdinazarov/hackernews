package kz.production.mukhit.hackernews.data.model

import com.google.gson.annotations.SerializedName

data class Comment (
    @SerializedName("by")
    val author : String? = null,

    @SerializedName("id")
    val id : Long? = null,

    @SerializedName("kids")
    val kids : List<Long>? = null,

    @SerializedName("parent")
    val parent : Long? = null,

    @SerializedName("time")
    val time : Long? = null,

    @SerializedName("text")
    val text : String? = null,

    @SerializedName("type")
    val type : String? = null
)