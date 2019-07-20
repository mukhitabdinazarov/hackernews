package kz.production.mukhit.hackernews.ui.main.presenter

import kz.production.mukhit.hackernews.ui.base.presenter.MVPPresenter
import kz.production.mukhit.hackernews.ui.main.interactor.MainMvpInteractor
import kz.production.mukhit.hackernews.ui.main.view.MainMvpView

interface MainMvpPresenter <V : MainMvpView, I : MainMvpInteractor> : MVPPresenter<V, I> {
}