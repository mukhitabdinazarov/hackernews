package kz.production.mukhit.hackernews.ui.stories.best.interactor

import io.reactivex.Observable
import io.reactivex.Single
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.data.network.ApiHelper
import kz.production.mukhit.hackernews.ui.base.interactor.BaseInteractor
import retrofit2.Response
import javax.inject.Inject

class BestInteractor @Inject internal constructor(apiHelper: ApiHelper) : BaseInteractor(apiHelper = apiHelper),
    BestMvpInteractor {

    override fun getBestStoryList(): Single<Response<List<Long>>> {
        return apiHelper.getBestStories()
    }

    override fun getStoryItem(itemId: Long): Observable<Story> {
        return apiHelper.getItem(itemId)
    }
}