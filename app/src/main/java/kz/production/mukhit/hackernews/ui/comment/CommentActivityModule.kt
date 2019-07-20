package kz.production.mukhit.hackernews.ui.comment

import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import kz.production.mukhit.hackernews.ui.comment.interactor.CommentInteractor
import kz.production.mukhit.hackernews.ui.comment.interactor.CommentMvpInteractor
import kz.production.mukhit.hackernews.ui.comment.presenter.CommentMvpPresenter
import kz.production.mukhit.hackernews.ui.comment.presenter.CommentPresenter
import kz.production.mukhit.hackernews.ui.comment.view.CommentActivity
import kz.production.mukhit.hackernews.ui.comment.view.CommentAdapter
import kz.production.mukhit.hackernews.ui.comment.view.CommentMvpView

@Module
class CommentActivityModule {

    @Provides
    internal fun provideCommentInteractor(CommentInteractor: CommentInteractor): CommentMvpInteractor = CommentInteractor

    @Provides
    internal fun provideCommentPresenter(CommentPresenter: CommentPresenter<CommentMvpView, CommentMvpInteractor>)
            : CommentMvpPresenter<CommentMvpView, CommentMvpInteractor> = CommentPresenter

    @Provides
    internal fun provideCommentAdapter(): CommentAdapter = CommentAdapter()

    @Provides
    internal fun provideLinearLayoutManager(activity: CommentActivity): LinearLayoutManager =
            LinearLayoutManager(activity)
}