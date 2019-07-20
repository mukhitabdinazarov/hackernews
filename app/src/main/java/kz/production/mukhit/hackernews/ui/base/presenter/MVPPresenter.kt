package kz.production.mukhit.hackernews.ui.base.presenter

import kz.production.mukhit.hackernews.ui.base.interactor.MVPInteractor
import kz.production.mukhit.hackernews.ui.base.view.MVPView

/**
 * Created by mukhit on 15/11/18.
 */
interface MVPPresenter<V : MVPView, I : MVPInteractor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}