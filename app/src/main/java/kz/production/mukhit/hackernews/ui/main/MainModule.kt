package kz.production.mukhit.hackernews.ui.main

import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import kz.production.mukhit.hackernews.ui.comment.view.CommentActivity
import kz.production.mukhit.hackernews.ui.comment.view.CommentAdapter
import kz.production.mukhit.hackernews.ui.main.interactor.MainInteractor
import kz.production.mukhit.hackernews.ui.main.interactor.MainMvpInteractor
import kz.production.mukhit.hackernews.ui.main.presenter.MainMvpPresenter
import kz.production.mukhit.hackernews.ui.main.presenter.MainPresenter
import kz.production.mukhit.hackernews.ui.main.view.MainMvpView
import org.w3c.dom.Comment

@Module
class MainModule {

    @Provides
    internal fun provideMainInteractor(mainInteractor: MainInteractor): MainMvpInteractor = mainInteractor

    @Provides
    internal fun provideMainPresenter(mainPresenter: MainPresenter<MainMvpView, MainMvpInteractor>)
            : MainMvpPresenter<MainMvpView, MainMvpInteractor> = mainPresenter


}