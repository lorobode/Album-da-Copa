package br.com.albumdacopa.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import br.com.albumdacopa.R
import br.com.albumdacopa.model.Sticker
import br.com.albumdacopa.ui.utils.ctx
import com.travijuu.numberpicker.library.Enums.ActionEnum
import com.travijuu.numberpicker.library.NumberPicker
import org.jetbrains.anko.find

/**
 * Created by Nichollas on 05/04/2018.
 */
class StickerListAdapter(private val stickerList: MutableList<Sticker>,
                         private val checkBoxClick: (Sticker, Boolean) -> Unit,
                         private val stickerValueChanged: (Sticker) -> Unit) :
        RecyclerView.Adapter<StickerListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_sticker, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(stickerList, position, checkBoxClick, stickerValueChanged)
    }

    override fun getItemCount(): Int = stickerList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val hasStickerCheckBox = view.find<CheckBox>(R.id.has_sticker)
        private val nameTextView = view.find<TextView>(R.id.sticker_name)
        private val numberTextView = view.find<TextView>(R.id.sticker_number)
        private val stickerCounter = view.find<NumberPicker>(R.id.sticker_counter)

        fun bindForecast(stickerList: MutableList<Sticker>,
                         position: Int,
                         checkBoxClick: (Sticker, Boolean) -> Unit,
                         stickerValueChanged: (Sticker) -> Unit) {
            with(stickerList[position]) {
                hasStickerCheckBox.setOnCheckedChangeListener(null)
                stickerCounter.valueChangedListener = null

                nameTextView.text = name
                numberTextView.text = if (number < 10) "0${number}" else "${number}"
                hasStickerCheckBox.isChecked = quantity > 0
                stickerCounter.visibility = View.GONE

                if (hasStickerCheckBox.isChecked) {
                    stickerCounter.visibility = View.VISIBLE
                    stickerCounter.value = quantity
                }

                hasStickerCheckBox.setOnCheckedChangeListener { _, isChecked: Boolean ->
                    if (isChecked) {
                        stickerCounter.visibility = View.VISIBLE
                        stickerList[position] = copy(quantity = 1)
                        stickerCounter.value = 1
                    } else {
                        stickerList[position] = copy(quantity = 0)
                        stickerCounter.visibility = View.GONE
                    }
                    checkBoxClick(stickerList[position], isChecked)
                }

                stickerCounter.setValueChangedListener { value, _ ->
                    if(value < 1) {
                        return@setValueChangedListener
                    }
                    stickerList[position] = copy(quantity = value)
                    stickerValueChanged(stickerList[position])
                }
            }
        }
    }
}