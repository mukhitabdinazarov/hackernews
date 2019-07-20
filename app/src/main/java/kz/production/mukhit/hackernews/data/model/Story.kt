package kz.production.mukhit.hackernews.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Story(

    @SerializedName("by")
    var author : String? = null,

    @SerializedName("descendants")
    var commentCount : Int? = 0,

    @SerializedName("id")
    var id : Long? = -1,

    @SerializedName("kids")
    var kids : List<Long>? = null,

    @SerializedName("score")
    var score : Int? = 0,

    @SerializedName("time")
    var time : Long? = null,

    @SerializedName("title")
    var title : String? = null,

    @SerializedName("type")
    var type : String? = null,

    @SerializedName("url")
    var url : String? = null



) : Serializable