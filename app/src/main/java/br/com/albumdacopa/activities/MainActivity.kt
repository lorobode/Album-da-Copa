package br.com.albumdacopa.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import br.com.albumdacopa.R
import br.com.albumdacopa.database.database
import br.com.albumdacopa.model.Sticker
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
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
                val textView: TextView = findViewById(R.id.first_sticker)
                textView.text = stickers[0].name
            }
        }
    }
}
