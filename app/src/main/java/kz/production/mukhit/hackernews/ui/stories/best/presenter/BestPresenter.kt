package kz.production.mukhit.hackernews.ui.stories.best.presenter

import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.ui.base.presenter.BasePresenter
import kz.production.mukhit.hackernews.ui.stories.best.interactor.BestMvpInteractor
import kz.production.mukhit.hackernews.ui.stories.best.view.BestMvpView
import kz.production.mukhit.hackernews.utils.SchedulerProvider
import javax.inject.Inject

class BestPresenter <V : BestMvpView, I : BestMvpInteractor> @Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) :
    BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    BestMvpPresenter<V, I> {

    private val TAG = "BestPresenter"
    private var observer : Disposable? = null


    override fun onLoadingBestStoryList() {
        interactor?.let {
            it.getBestStoryList()
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
        observer = Observable.just(list)
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

    override fun cancelAllLoading() {
        observer?.dispose()
    }
}