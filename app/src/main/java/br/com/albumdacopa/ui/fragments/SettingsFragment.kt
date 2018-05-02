package br.com.albumdacopa.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.albumdacopa.R
import br.com.albumdacopa.database.database
import br.com.albumdacopa.model.Sticker
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import android.content.Intent



class SettingsFragment : Fragment() {

    val OWNED = "owned"
    val REPEATED = "repeated"
    val MISSING = "missing"

    private lateinit var repeatedStickers: List<Sticker>
    private lateinit var ownedStickers: List<Sticker>
    private lateinit var missingStickers: List<Sticker>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_settings, container, false)

        val repeatedStickersTV = view.find<TextView>(R.id.repeated_stickers_tv)
        val ownedStickersTV = view.find<TextView>(R.id.owned_stickers_tv)
        val missingStickersTV = view.find<TextView>(R.id.missing_stickers_tv)


        doAsync {
            repeatedStickers = Sticker.repeated(activity.database)
            ownedStickers = Sticker.owned(activity.database)
            missingStickers = Sticker.missing(activity.database)
            uiThread {
                repeatedStickersTV.text = getString(R.string.repeated_stickers_text, repeatedStickers.count())
                repeatedStickersTV.setOnClickListener { shareStickers(REPEATED) }
                ownedStickersTV.text = getString(R.string.owned_stickers_text, ownedStickers.count())
                ownedStickersTV.setOnClickListener { shareStickers(OWNED) }
                missingStickersTV.text = getString(R.string.missing_stickers_text, missingStickers.count())
                missingStickersTV.setOnClickListener { shareStickers(MISSING) }
            }
        }

        return view
    }

    private fun shareStickers(type: String) {

        var textToShare =
                when (type) {
                    REPEATED -> "Essas são minhas figurinhas repetidas: \n"
                    OWNED -> "Essas são as figurinhas que tenho: \n"
                    else -> "Essas são as figurinhas que faltam: \n"
                }
        val stickers: List<Sticker> =
                when (type) {
                    REPEATED -> repeatedStickers
                    OWNED -> ownedStickers
                    else -> missingStickers
                }

        stickers.forEach {
            textToShare += "${it.number}, "
        }

        textToShare = textToShare.substring(0, textToShare.length - 2)

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare)
        startActivity(Intent.createChooser(shareIntent, "Compartilhe:"))
    }

}