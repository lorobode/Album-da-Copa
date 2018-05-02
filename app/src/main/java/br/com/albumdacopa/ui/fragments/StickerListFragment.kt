package br.com.albumdacopa.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.albumdacopa.R
import br.com.albumdacopa.eventbus.RxBus
import br.com.albumdacopa.eventbus.StickerCheckBoxEvent
import br.com.albumdacopa.eventbus.StickerCounterChangeEvent
import br.com.albumdacopa.eventbus.StickerListEvent
import br.com.albumdacopa.model.Sticker
import br.com.albumdacopa.model.addToAlbum
import br.com.albumdacopa.model.removeFromAlbum
import br.com.albumdacopa.model.updateQuantity
import br.com.albumdacopa.ui.activities.DrawerActivity
import br.com.albumdacopa.ui.adapter.StickerListAdapter
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_sticker_list.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*
import kotlin.concurrent.schedule


class StickerListFragment : Fragment() {

    private var type: String? = null
    private var inForeground: Boolean = false

    private lateinit var stickerListDisposable: Disposable
    private lateinit var stickerCheckboxDisposable: Disposable
    private lateinit var stickerCounterChangeDisposable: Disposable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sticker_list, container, false)
        type = arguments?.getString(TYPE)

        stickerListDisposable = RxBus.listen(StickerListEvent::class.java).subscribe {
            setStickerList(it)
        }

        stickerCheckboxDisposable = RxBus.listen(StickerCheckBoxEvent::class.java).subscribe {
            stickerCheckboxEvent(it)
        }
        stickerCounterChangeDisposable = RxBus.listen(StickerCounterChangeEvent::class.java).subscribe {
            stickerCounterChangeEvent(it)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadStickerList()
    }

    override fun onDestroy() {
        super.onDestroy()

        stickerCheckboxDisposable.dispose()
        stickerCounterChangeDisposable.dispose()
        stickerListDisposable.dispose()
    }

    override fun onResume() {
        super.onResume()
        // This Fragment is now in the foreground.
        inForeground = true
    }

    override fun onPause() {
        super.onPause()
        // This Fragment is now NOT in the foreground.
        inForeground = false
    }

    private fun loadStickerList() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        //Use this now
        recyclerView.addItemDecoration(MaterialViewPagerHeaderDecorator())


        val checkBoxChecked = { sticker: Sticker, isChecked: Boolean ->
            RxBus.publish(StickerCheckBoxEvent(sticker, type!!, isChecked))
        }
        val stickerValueChanged = { sticker: Sticker, value: Int ->
            RxBus.publish(StickerCounterChangeEvent(sticker, type!!, value))
        }
        recyclerView.adapter = StickerListAdapter(ArrayList(), type!!, checkBoxChecked, stickerValueChanged)

        val timer = Timer("load list", true)
        timer.schedule(400) {
            (activity as DrawerActivity).loadStickers()
        }
    }

    //Event Listener methods
    private fun setStickerList(stickerListEvent: StickerListEvent) {

        with(stickerListEvent) {
            val stickerList: List<Sticker> =
                    when (type) {
                        ALL_STICKERS -> allStickers
                        REPEATED_STICKERS -> repeatedStickers
                        else -> allStickers
                    }
            doAsync {
                uiThread {
                    (recyclerView.adapter as StickerListAdapter).setStickerList(stickerList)
                    recyclerView.adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun stickerCheckboxEvent(stickerCheckBoxEvent: StickerCheckBoxEvent) {
        with(stickerCheckBoxEvent) {
            if (this@StickerListFragment.type == type) {
                if (isChecked) sticker.addToAlbum(activity.ctx) else sticker.removeFromAlbum(activity.ctx)
            } else {
                if (!isChecked) {
                    (recyclerView.adapter as StickerListAdapter).removeSticker(sticker)
                    recyclerView.adapter.notifyDataSetChanged()
                }
            }

            if (this@StickerListFragment.type == REPEATED_STICKERS && !isChecked) {
                (recyclerView.adapter as StickerListAdapter).removeSticker(sticker)
                recyclerView.adapter.notifyDataSetChanged()
            }
        }
    }

    private fun stickerCounterChangeEvent(stickerCounterChangeEvent: StickerCounterChangeEvent) {
        with(stickerCounterChangeEvent) {
            if (this@StickerListFragment.type == type) {
                sticker.updateQuantity(activity!!.ctx)
            }
            (recyclerView.adapter as StickerListAdapter).updateStickerCount(sticker)
        }
    }

    companion object {
        val TYPE = "type"
        val ALL_STICKERS = "all_stickers"
        val REPEATED_STICKERS = "repeated_stickers"


        fun newInstance(type: String): StickerListFragment {
            val fragment = StickerListFragment()
            val args = Bundle()
            args.putString(TYPE, type)
            fragment.arguments = args
            return fragment
        }
    }
}