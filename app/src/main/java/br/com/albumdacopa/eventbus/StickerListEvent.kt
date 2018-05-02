package br.com.albumdacopa.eventbus

import br.com.albumdacopa.model.Sticker

/**
 * Created by Nichollas on 17/04/2018.
 */
data class StickerListEvent(val allStickers: List<Sticker>, val repeatedStickers: List<Sticker>) {
}