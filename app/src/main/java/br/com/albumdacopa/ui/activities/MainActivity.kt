package br.com.albumdacopa.ui.activities

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import br.com.albumdacopa.R
import br.com.albumdacopa.ui.adapter.PageAdapter
import br.com.albumdacopa.ui.fragments.StickerListFragment
import com.github.florent37.materialviewpager.MaterialViewPager
import com.github.florent37.materialviewpager.header.HeaderDesign
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find


class MainActivity : DrawerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)

        val pageAdapter = PageAdapter(supportFragmentManager)

        /*val toolbar: Toolbar = materialViewPager.toolbar
        setSupportActionBar(toolbar)

        pageAdapter.add(StickerListFragment.newInstance(StickerListFragment.ALL_STICKERS))
        pageAdapter.add(StickerListFragment.newInstance(StickerListFragment.REPEATED_STICKERS))

        materialViewPager.viewPager.adapter = pageAdapter

        materialViewPager.setMaterialViewPagerListener(MaterialViewPager.Listener { page ->
            when (page) {
                0 -> return@Listener HeaderDesign.fromColorResAndDrawable(R.color.green, ContextCompat.getDrawable(ctx, R.drawable.background))
                1 -> return@Listener HeaderDesign.fromColorResAndDrawable(R.color.red, ContextCompat.getDrawable(ctx, R.drawable.background))
            }
            null
        })

        materialViewPager.viewPager.offscreenPageLimit = (materialViewPager.viewPager.adapter as PageAdapter).count
        materialViewPager.pagerTitleStrip.setViewPager(materialViewPager.viewPager)*/

        super.onCreate(savedInstanceState)

        /*findViewById<View>(R.id.logo_white)?.setOnClickListener {
            materialViewPager.notifyHeaderChanged()
            Toast.makeText(applicationContext, "Yes, the title is clickable", Toast.LENGTH_SHORT).show()
        }*/

    }

    /*private fun loadStickerList() {
        stickerList.layoutManager = LinearLayoutManager(ctx)
        fab.setOnClickListener { stickerList.scrollToPosition(0) }

        doAsync {
            uiThread {
                val stickers = Sticker.list(database)
                val checkBoxChecked = { sticker: Sticker, isChecked: Boolean ->
                    if (isChecked) sticker.addToAlbum(ctx) else sticker.removeFromAlbum(ctx)
                }
                val stickerValueChanged = {sticker: Sticker ->
                    sticker.updateQuantity(ctx)
                }

                stickerList.adapter = StickerListAdapter(stickers.toMutableList(), checkBoxChecked, stickerValueChanged)
            }
        }
    }*/

}