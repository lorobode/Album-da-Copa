package br.com.albumdacopa.eventbus

import br.com.albumdacopa.model.Sticker

/**
 * Created by Nichollas on 17/04/2018.
 */
data class StickerCheckBoxEvent(val sticker: Sticker, val type:String, val isChecked:Boolean) {
}