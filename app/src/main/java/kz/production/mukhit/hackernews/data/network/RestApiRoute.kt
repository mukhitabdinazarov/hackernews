package kz.production.mukhit.hackernews.data.network

import kz.production.mukhit.hackernews.utils.AppConstants


object RestApiRoute {

    const val TOP_STORIES = "${AppConstants.BASE_URL}topstories.json"
    const val NEW_StORIES = "${AppConstants.BASE_URL}newstories.json"
    const val BEST_STORIES = "${AppConstants.BASE_URL}beststories.json"


}