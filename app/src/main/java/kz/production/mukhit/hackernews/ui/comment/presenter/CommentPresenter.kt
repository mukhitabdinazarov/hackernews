package kz.production.mukhit.hackernews.ui.comment.presenter

import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kz.production.mukhit.hackernews.data.model.Comment
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.data.remote.entity.CommentEntity
import kz.production.mukhit.hackernews.ui.base.presenter.BasePresenter
import kz.production.mukhit.hackernews.ui.comment.interactor.CommentMvpInteractor
import kz.production.mukhit.hackernews.ui.comment.view.CommentMvpView
import kz.production.mukhit.hackernews.utils.SchedulerProvider
import retrofit2.Response
import javax.inject.Inject

class CommentPresenter <V : CommentMvpView, I : CommentMvpInteractor>
        @Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) :
        BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable),
        CommentMvpPresenter<V, I> {


    private val TAG = "CommentPresenter"


    override fun onLoadStory(storyId : Long) {
        interactor?.let {
            it.getStory(storyId)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({response->
                    getView()?.setStory(response)
                },{error->

                })
        }
    }


    override fun onLoadComments(commentIdList: List<Long>?) {
        val observer = Observable.just(commentIdList)
            .flatMap{numberList -> Observable.fromIterable(numberList)}
            .flatMap{number -> getItemObservable(number)}
            .compose(schedulerProvider.ioToMainObservableScheduler())
            .toList()
            .subscribe({response->
                getView()?.onSetComment(response)
            },{error->
                    Log.e(TAG,error.message,error)
            })
    }



    override fun onLoadReplies(commentIdList: List<Long>,position : Int, commentId : Long) {
        val observer = Observable.just(commentIdList)
            .flatMap{numberList -> Observable.fromIterable(numberList)}
            .flatMap{number -> getReplyItemObservable(number)}
            .compose(schedulerProvider.ioToMainObservableScheduler())
            .toList()
            .subscribe({response->

                getView()?.onSetCommentReplies(response,position,commentId)

            },{error->
                Log.e(TAG,error.message,error)
            })

    }

    override fun getItemObservable(id: Long) = interactor?.getComment(id)!!

    override fun getReplyItemObservable(id: Long) = interactor?.getReplies(id)!!



    override fun getRemoteComments(parentId : Long?,limit : Int,offset : Int) {
        if(parentId!=null) {
            interactor?.let {
                it.getRemoteComments(parentId, limit, offset)
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .subscribe({ response ->
                        getView()?.onSetRemoteComments(response)
                    }, { error ->
                        Log.e(TAG,"Failed loading comment with error message " + error.message,error)
                    })
            }
        }
    }

    override fun getRemoteComments(parentId: Long?) = interactor?.getRemoteComments(parentId!!)


    override fun getRemoteReplies(parentId: Long?, position : Int) {
        if(parentId!=null) {
            interactor?.let {
                it.getRemoteComments(parentId)
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .subscribe({ response ->
                        getView()?.onSetRemoteReplies(response,position,parentId)
                    }, { error ->
                        Log.e(TAG, "Failed loading comment with error message " + error.message, error)
                    })
            }
        }
    }



    override fun insertComment(commentEntity: CommentEntity) {
        interactor?.let {
            it.insertToDb(commentEntity)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({respnse->

                },{error->
                    Log.e(TAG,"Failed inserting comment with error message " + error.message,error)
                })
        }
    }
}