package kz.production.mukhit.hackernews.ui.stories.top.interactor

import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.ui.base.interactor.MVPInteractor
import retrofit2.Response

interface TopMvpInteractor : MVPInteractor {

    fun getTopStoryList() : Single<Response<List<Long>>>

    fun getStoryItem(itemId : Long) : Observable<Story>
}