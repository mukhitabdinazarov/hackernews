package kz.production.mukhit.hackernews.ui.base.presenter

import io.reactivex.disposables.CompositeDisposable
import kz.production.mukhit.hackernews.ui.base.interactor.MVPInteractor
import kz.production.mukhit.hackernews.ui.base.view.MVPView
import kz.production.mukhit.hackernews.utils.SchedulerProvider

/**
 * Created by mukhit on 15/11/18.
 */
abstract class BasePresenter<V : MVPView, I : MVPInteractor> internal constructor(protected var interactor: I?, protected val schedulerProvider: SchedulerProvider, protected val compositeDisposable: CompositeDisposable) : MVPPresenter<V, I> {

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
        interactor = null
    }

}