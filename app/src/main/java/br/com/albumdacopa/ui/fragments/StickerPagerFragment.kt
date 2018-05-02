package br.com.albumdacopa.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.albumdacopa.R
import br.com.albumdacopa.ui.adapter.PageAdapter
import org.jetbrains.anko.find


class StickerPagerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sitcker_pager, container, false)


        val vpPager = view.find<ViewPager>(R.id.vpPager)
        val adapterViewPager = PageAdapter(childFragmentManager)
        adapterViewPager.add(StickerListFragment.newInstance(StickerListFragment.ALL_STICKERS))
        adapterViewPager.add(StickerListFragment.newInstance(StickerListFragment.REPEATED_STICKERS))
        vpPager.adapter = adapterViewPager

        return view
    }
}