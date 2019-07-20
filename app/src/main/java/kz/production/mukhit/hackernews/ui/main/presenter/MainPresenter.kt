package kz.production.mukhit.hackernews.ui.main.presenter

import io.reactivex.disposables.CompositeDisposable
import kz.production.mukhit.hackernews.ui.base.presenter.BasePresenter
import kz.production.mukhit.hackernews.ui.main.interactor.MainMvpInteractor
import kz.production.mukhit.hackernews.ui.main.view.MainMvpView
import kz.production.mukhit.hackernews.utils.SchedulerProvider
import javax.inject.Inject

class MainPresenter <V : MainMvpView, I : MainMvpInteractor>
        @Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) :
        BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable),
        MainMvpPresenter<V, I> {


    /*override fun getShopList(){
        interactor.let {
            it?.getShopList()
                    ?.compose(schedulerProvider.ioToMainObservableScheduler())
                    ?.subscribe {defModel->
                        getView()?.let {view->
                            defModel.result.let {shopList->
                                view.showShopList(shopList!!)
                            }
                        }

                    }

        }
    }*/

}