package kz.production.mukhit.hackernews.ui.base.view

/**
 * Created by mukhit on 15/11/18.
 */
interface MVPView {

    fun showProgress()

    fun hideProgress()

    fun showMessage(message: String)

    fun isNetworkAvailable() : Boolean

}