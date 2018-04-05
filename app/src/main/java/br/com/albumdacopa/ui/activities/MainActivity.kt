package br.com.albumdacopa.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import br.com.albumdacopa.R
import br.com.albumdacopa.database.database
import br.com.albumdacopa.model.Sticker
import br.com.albumdacopa.ui.adapter.StickerListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadAsync()
    }

    private fun loadAsync() {
        doAsync {
            uiThread {
                val stickers = database.use {
                    select(Sticker.TABLE_NAME).exec { parseList(classParser<Sticker>()) }
                }

                val stickerList: RecyclerView = find(R.id.stickers_list)
                stickerList.layoutManager = LinearLayoutManager(ctx)
                stickerList.adapter = StickerListAdapter(stickers)
                fab.setOnClickListener {
                    stickerList.scrollToPosition(0)
                }
            }
        }
    }
}
