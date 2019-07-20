package kz.production.mukhit.hackernews.ui.main.interactor

import kz.production.mukhit.hackernews.data.network.ApiHelper
import kz.production.mukhit.hackernews.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class  MainInteractor
@Inject internal constructor(apiHelper: ApiHelper) :
        BaseInteractor(apiHelper = apiHelper), MainMvpInteractor {

}