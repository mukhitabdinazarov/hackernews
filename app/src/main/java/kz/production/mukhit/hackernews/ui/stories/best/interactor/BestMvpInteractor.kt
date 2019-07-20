package kz.production.mukhit.hackernews.ui.stories.best.interactor

import io.reactivex.Observable
import io.reactivex.Single
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.ui.base.interactor.MVPInteractor
import retrofit2.Response

interface BestMvpInteractor : MVPInteractor {

    fun getBestStoryList() : Single<Response<List<Long>>>

    fun getStoryItem(itemId : Long) : Observable<Story>
}