package kz.production.mukhit.hackernews.ui.base.interactor

import kz.production.mukhit.hackernews.data.network.ApiHelper


open class BaseInteractor() : MVPInteractor {

    protected lateinit var apiHelper: ApiHelper

    constructor(apiHelper: ApiHelper) : this() {
        this.apiHelper = apiHelper
    }



}