package kz.production.mukhit.hackernews.ui.main.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import kz.production.mukhit.hackernews.ui.stories.top.view.TopFragment

class MainPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private var tabCount = 0

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment? {
        val fragment = TopFragment()
        val bundle = Bundle()
        when (position) {
            0 -> bundle.putString("type","new")
            1-> bundle.putString("type","top")
            2 -> bundle.putString("type","best")
            else -> bundle.putString("type","new")
        }

        fragment.arguments = bundle
        return fragment
    }

    internal fun setCount(count: Int) {
        this.tabCount = count
    }

}