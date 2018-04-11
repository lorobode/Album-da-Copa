package br.com.albumdacopa.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import br.com.albumdacopa.R
import br.com.albumdacopa.database.database
import br.com.albumdacopa.model.*
import br.com.albumdacopa.ui.adapter.StickerListAdapter
import com.travijuu.numberpicker.library.Enums.ActionEnum
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private val stickerList: RecyclerView by lazy {
        find<RecyclerView>(R.id.stickers_list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadStickerList()
    }

    private fun loadStickerList() {
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
    }
}