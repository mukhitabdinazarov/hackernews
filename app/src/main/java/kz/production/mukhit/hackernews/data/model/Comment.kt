package kz.production.mukhit.hackernews.data.model

import com.google.gson.annotations.SerializedName

data class Comment (

    @SerializedName("by")
    var author : String? = null,

    @SerializedName("id")
    var id : Long? = null,

    @SerializedName("kids")
    var kids : List<Long>? = null,

    @SerializedName("parent")
    var parent : Long? = null,

    @SerializedName("time")
    var time : Long? = null,

    @SerializedName("text")
    var text : String? = null,

    @SerializedName("type")
    var type : String? = null
)