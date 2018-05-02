package br.com.albumdacopa.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.albumdacopa.R

/**
 * Created by Nichollas on 12/04/2018.
 */
class StickerListAdapterTemp(private val contents: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val header = 0
    private val cell = 1

    override fun getItemViewType(position: Int): Int {
        when (position) {
            0 -> return header
            else -> return cell
        }
    }

    override fun getItemCount(): Int {
        return contents.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        var view: View? = null

        when (viewType) {
            header -> {
                view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_card_big, parent, false)
                return object : RecyclerView.ViewHolder(view!!) {

                }
            }
            cell -> {
                view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_card_small, parent, false)
                return object : RecyclerView.ViewHolder(view!!) {

                }
            }
        }
        return null
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            header -> {
            }
            cell -> {
            }
        }
    }
}
