package kz.production.mukhit.hackernews.ui.main.view

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kz.production.mukhit.hackernews.R
import kz.production.mukhit.hackernews.ui.base.view.BaseActivity
import kz.production.mukhit.hackernews.ui.main.interactor.MainMvpInteractor
import kz.production.mukhit.hackernews.ui.main.presenter.MainMvpPresenter
import kz.production.mukhit.hackernews.ui.main.view.MainPagerAdapter
import kz.production.mukhit.hackernews.utils.extension.removeFragment
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMvpView, HasSupportFragmentInjector {

    @Inject
    internal lateinit var presenter: MainMvpPresenter<MainMvpView, MainMvpInteractor>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    internal lateinit var mainPagerAdapter: MainPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onAttach(this)

        mainPagerAdapter = MainPagerAdapter(supportFragmentManager)
        setUpMainPagerAdapter()

    }

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    override fun onFragmentDetached(tag: String) {
        supportFragmentManager.removeFragment(tag  = tag)
    }

    override fun onFragmentAttached() {
    }

    override fun setUpMainPagerAdapter() {
        mainPagerAdapter.count = 3
        mainViewPager.adapter = mainPagerAdapter
        tabLayout.addTab(tabLayout.newTab().setText(R.string.new_stories))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.top_stories))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.best_stories))
        mainViewPager.offscreenPageLimit = tabLayout.tabCount;
        mainViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                mainViewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }


}
