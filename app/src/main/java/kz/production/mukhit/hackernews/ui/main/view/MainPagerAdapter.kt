package kz.production.mukhit.hackernews.ui.main.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import kz.production.mukhit.hackernews.ui.stories.best.view.BestFragment
import kz.production.mukhit.hackernews.ui.stories.last.view.LastFragment
import kz.production.mukhit.hackernews.ui.stories.top.view.TopFragment

class MainPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private var tabCount = 0

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> LastFragment()
            1 -> TopFragment()
            2 -> BestFragment()
            else -> null
        }
    }

    internal fun setCount(count: Int) {
        this.tabCount = count
    }

}