package br.com.albumdacopa.database

import android.content.Context
import android.content.res.Resources
import android.database.sqlite.SQLiteDatabase
import br.com.albumdacopa.R
import br.com.albumdacopa.model.Sticker
import org.jetbrains.anko.db.*

/**
 * Created by Nichollas on 05/04/2018.
 */
class MySqlHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "albumdb") {

    companion object {
        private var instance: MySqlHelper? = null
        private var res:Resources? = null

        @Synchronized
        fun getInstance(ctx: Context): MySqlHelper {
            if (instance == null) {
                instance = MySqlHelper(ctx.applicationContext)
                res = ctx.resources
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Sticker.TABLE_NAME, true,
                "_id" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Sticker.COLUMN_NAME to TEXT,
                Sticker.COLUMN_NUMBER to INTEGER,
                Sticker.COLUMN_TYPE to TEXT)

        val customStickers = res?.getStringArray(R.array.custom_stickers)

        for (i in 0..7) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (customStickers?.get(i) ?: "custom"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "custom"
            )
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable("Sticker", true)
    }

}

// Access property for Context
val Context.database: MySqlHelper
    get() = MySqlHelper.getInstance(applicationContext)