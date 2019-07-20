package kz.production.mukhit.hackernews.ui.stories.last.interactor

import io.reactivex.Observable
import io.reactivex.Single
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.data.network.ApiHelper
import kz.production.mukhit.hackernews.ui.base.interactor.BaseInteractor
import retrofit2.Response
import javax.inject.Inject

class LastInteractor @Inject internal constructor(apiHelper: ApiHelper) : BaseInteractor(apiHelper = apiHelper), LastMvpInteractor {


    override fun getLastStoryList(): Single<Response<List<Long>>> {
        return apiHelper.getNewStories()
    }

    override fun getStoryItem(itemId: Long): Observable<Story> {
        return apiHelper.getItem(itemId)
    }
}