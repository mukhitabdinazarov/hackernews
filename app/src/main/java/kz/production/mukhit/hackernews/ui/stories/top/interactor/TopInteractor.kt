package kz.production.mukhit.hackernews.ui.stories.top.interactor

import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.data.network.ApiHelper
import kz.production.mukhit.hackernews.ui.base.interactor.BaseInteractor
import retrofit2.Response
import javax.inject.Inject

class TopInteractor
@Inject internal constructor(apiHelper: ApiHelper) :
    BaseInteractor(apiHelper = apiHelper), TopMvpInteractor {


    override fun getTopStoryList(): Single<Response<List<Long>>> {
        return apiHelper.getTopStories()
    }

    override fun getStoryItem(itemId: Long): Observable<Story> {
        return apiHelper.getItem(itemId)
    }
}