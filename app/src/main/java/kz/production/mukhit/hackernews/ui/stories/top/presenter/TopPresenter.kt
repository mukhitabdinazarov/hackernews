package kz.production.mukhit.hackernews.ui.stories.top.presenter

import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kz.production.mukhit.hackernews.ui.stories.top.interactor.TopMvpInteractor
import kz.production.mukhit.hackernews.ui.stories.top.view.TopView
import java.util.*
import javax.inject.Inject
import com.google.gson.JsonObject
import io.reactivex.disposables.Disposable
import kz.production.mukhit.hackernews.data.model.Story
import kz.production.mukhit.hackernews.data.remote.entity.StoryEntity
import kz.production.mukhit.hackernews.ui.base.presenter.BasePresenter
import kz.production.mukhit.hackernews.utils.SchedulerProvider
import retrofit2.Response


class TopPresenter <V : TopView, I : TopMvpInteractor> @Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) :
    BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    TopMvpPresenter<V, I> {

    private val TAG = "TopPresenter"
    private var observer : Disposable? = null

    override fun onLoadingStoryList(type : Int) {

        interactor?.let {
            it.getStoryList(type)
                .compose(schedulerProvider.ioToMainSingleScheduler())
                .subscribe({response->
                    Log.d(TAG,"onLoadingTopStoryList() " + response.message())
                    if(response.isSuccessful){
                        getView()?.setList(response.body())
                    }
                }, {error->
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

    private fun getItemObservable(id: Long) : Observable<Story> {
        return interactor?.getStoryItem(id)!!
    }

    override fun cancelAllLoading() {
        observer?.dispose()
    }

    //room
    override fun storyIsEmpty(story: StoryEntity){
        interactor?.let {
            it.storyIsEmpty(story.id!!.toLong())
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({ response ->
                    if (response) {}
                }, {
                })
        }

    }

    override fun loadStory(story : StoryEntity?) {
        interactor?.let {
            it.getStoryById(story?.id!!)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({ response ->
                    if(response != null){
                        response.isTop = true
                        updateStory(response)
                    }
                    else{
                        saveStory(story)
                    }

                }, { error ->
                    saveStory(story)
                })
        }

    }

    override fun saveStory(story: StoryEntity) {
        interactor?.let {
            it.insertStory(story)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({response->
                    if(response){

                    }
                    else{

                    }
                },{error->

                })
        }
    }

    override fun updateStory(story: StoryEntity) {
        interactor?.let {
            it.updateStory(story)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({response->
                    if(response){

                    }
                    else{

                    }
                },{error->

                })
        }
    }

    override fun loadRemoteStoryList(limit: Int, offset: Int,type : Int) {
        interactor?.let {
            it.getRemoteStories(limit, offset, type)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({response->
                    if(response!=null)
                        getView()?.setAllStoryInDb(response)
                },{error->

                })
        }
    }
}