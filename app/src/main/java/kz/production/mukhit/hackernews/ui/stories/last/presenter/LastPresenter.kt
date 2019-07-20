package kz.production.mukhit.hackernews.ui.stories.last.presenter

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.ui.base.presenter.BasePresenter
import kz.production.mukhit.hackernews.ui.stories.last.interactor.LastMvpInteractor
import kz.production.mukhit.hackernews.ui.stories.last.view.LastMvpView
import kz.production.mukhit.hackernews.utils.SchedulerProvider
import retrofit2.Response
import javax.inject.Inject

class LastPresenter <V : LastMvpView, I : LastMvpInteractor> @Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) :
    BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    LastMvpPresenter<V, I> {

    private val TAG = "LastPresenter"


    override fun onLoadingLastStoryList() {
        interactor?.let {
            it.getLastStoryList()
                .compose(schedulerProvider.ioToMainSingleScheduler())
                .subscribe({success->
                    if(success.isSuccessful){
                        getView()?.setList(success.body())
                    }
                    else{

                    }
                },{error->
                    Log.e(TAG,error.message,error)
                })
        }
    }

    override fun onLoadStoryItem(list: List<Long>?) {
        val observer = Observable.just(list)
            .flatMap{numberList -> Observable.fromIterable(numberList)}
            .flatMap{number -> getItemObservable(number)}
            .compose(schedulerProvider.ioToMainObservableScheduler())
            .toList()
            .subscribe({response->
                getView()?.setStoryList(response)
            },{error->
                Log.e(TAG,error.message,error)
            })
    }

    override fun getItemObservable(id: Long) = interactor?.getStoryItem(id)!!

}