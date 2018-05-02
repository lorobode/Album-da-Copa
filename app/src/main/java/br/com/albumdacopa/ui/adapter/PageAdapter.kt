package br.com.albumdacopa.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Nichollas on 12/04/2018.
 */
class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragments: ArrayList<Fragment> = ArrayList()

    fun add(fragment: Fragment) {
        fragments.add(fragment)
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        when (position % 2) {
            0 -> return "Todas"
            1 -> return "Repetidas"
        }
        return ""
    }
}