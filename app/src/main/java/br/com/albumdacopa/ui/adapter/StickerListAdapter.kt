package br.com.albumdacopa.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.albumdacopa.R
import br.com.albumdacopa.model.Sticker
import br.com.albumdacopa.ui.utils.ctx
import org.jetbrains.anko.find

/**
 * Created by Nichollas on 05/04/2018.
 */
class StickerListAdapter(private val stickerList: List<Sticker>) :
        RecyclerView.Adapter<StickerListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_sticker, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(stickerList[position])
    }

    override fun getItemCount(): Int = stickerList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameTextView = view.find<TextView>(R.id.sticker_name)
        private val numberTextView = view.find<TextView>(R.id.sticker_number)

        fun bindForecast(sticker: Sticker) {
            with(sticker) {
                nameTextView.text = sticker.name
                numberTextView.text = if (sticker.number < 10) "0${sticker.number}" else "${sticker.number}"
            }
        }
    }
}