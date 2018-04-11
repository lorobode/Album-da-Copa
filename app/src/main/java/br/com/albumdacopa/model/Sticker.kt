package br.com.albumdacopa.model

import android.content.Context
import br.com.albumdacopa.database.MySqlHelper
import br.com.albumdacopa.database.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update

/**
 * Created by Nichollas on 05/04/2018.
 */
data class Sticker(val id: Int, val name: String, val number: Int, val type: String, val quantity: Int) {
    companion object {
        val TABLE_NAME = "Sticker"
        val COLUMN_NAME = "name"
        val COLUMN_NUMBER = "number"
        val COLUMN_TYPE = "type"
        val COLUMN_QUANTITY = "quantity"

        fun list(database: MySqlHelper): List<Sticker> {
            return database.use {
                select(Sticker.TABLE_NAME).exec { parseList(classParser<Sticker>()) }
            }
        }
    }
}

fun Sticker.addToAlbum(ctx: Context) {
    ctx.database.use {
        update("Sticker", "quantity" to 1)
                .whereSimple("_id=?", id.toString())
                .exec()
    }
}

fun Sticker.removeFromAlbum(ctx: Context) {
    ctx.database.use {
        update("Sticker", "quantity" to 0)
                .whereSimple("_id=?", id.toString())
                .exec()
    }
}

fun Sticker.updateQuantity(ctx: Context) {
    ctx.database.use {
        update("Sticker", "quantity" to quantity)
                .whereSimple("_id=?", id.toString())
                .exec()
    }
}