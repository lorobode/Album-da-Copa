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

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Sticker.TABLE_NAME, true,
                "_id" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Sticker.COLUMN_NAME to TEXT,
                Sticker.COLUMN_NUMBER to INTEGER,
                Sticker.COLUMN_TYPE to TEXT,
                Sticker.COLUMN_QUANTITY to INTEGER
                )

        val customStickers = res?.getStringArray(R.array.custom_stickers)
        val stadiumStickers = res?.getStringArray(R.array.stadium_stickers)
        val hostCitiesStickers = res?.getStringArray(R.array.host_cities_stickers)
        val russiaStickers = res?.getStringArray(R.array.russia_stickers)
        val saudiArabiaStickers = res?.getStringArray(R.array.saudi_arabia_stickers)
        val egyptStickers = res?.getStringArray(R.array.egypt_stickers)
        val uruguayStickers = res?.getStringArray(R.array.uruguay_stickers)
        val portugalStickers = res?.getStringArray(R.array.portugal_stickers)
        val spainStickers = res?.getStringArray(R.array.spain_stickers)
        val moroccoStickers = res?.getStringArray(R.array.morocco_stickers)
        val iranStickers = res?.getStringArray(R.array.iran_stickers)
        val franceStickers = res?.getStringArray(R.array.france_stickers)
        val australiaStickers = res?.getStringArray(R.array.australia_stickers)
        val peruStickers = res?.getStringArray(R.array.peru_stickers)
        val denmarkStickers = res?.getStringArray(R.array.denmark_stickers)
        val argentinaStickers = res?.getStringArray(R.array.argentina_stickers)
        val icelandStickers = res?.getStringArray(R.array.iceland_stickers)
        val croatiaStickers = res?.getStringArray(R.array.croatia_stickers)
        val nigeriaStickers = res?.getStringArray(R.array.nigeria_stickers)
        val brazilStickers = res?.getStringArray(R.array.brazil_stickers)
        val switzerlandStickers = res?.getStringArray(R.array.switzerland_stickers)
        val costaRicaStickers = res?.getStringArray(R.array.costa_rica_stickers)
        val serbiaStickers = res?.getStringArray(R.array.serbia_stickers)
        val germanyStickers = res?.getStringArray(R.array.germany_stickers)
        val mexicoStickers = res?.getStringArray(R.array.mexico_stickers)
        val swedenStickers = res?.getStringArray(R.array.sweden_stickers)
        val koreaRepublicStickers = res?.getStringArray(R.array.korea_republic_stickers)
        val belgiumStickers = res?.getStringArray(R.array.belgium_stickers)
        val panamaStickers = res?.getStringArray(R.array.panama_stickers)
        val tunisiaStickers = res?.getStringArray(R.array.tunisia_stickers)
        val englandStickers = res?.getStringArray(R.array.england_stickers)
        val polandStickers = res?.getStringArray(R.array.poland_stickers)
        val senegalStickers = res?.getStringArray(R.array.senegal_stickers)
        val colombiaStickers = res?.getStringArray(R.array.colombia_stickers)
        val japanStickers = res?.getStringArray(R.array.japan_stickers)
        val legendsStickers = res?.getStringArray(R.array.legends_stickers)

        //Seed custom stickers
        for (i in 0..7) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (customStickers?.get(i) ?: "custom"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Custom",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed stadium stickers
        for (i in 8..19) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (stadiumStickers?.get(i-8) ?: "stadium"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Stadium",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed host cities stickers
        for (i in 20..31) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (hostCitiesStickers?.get(i-20) ?: "host city"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Host City",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed russia team stickers
        for (i in 32..51) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (russiaStickers?.get(i-32) ?: "russia"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Russia",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed saudi arabia team stickers
        for (i in 52..71) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (saudiArabiaStickers?.get(i-52) ?: "saudi arabia"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Saudi Arabia",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Egypt Team stickers
        for (i in 72..91) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (egyptStickers?.get(i-72) ?: "egypt"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Egypt Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Uruguay Team stickers
        for (i in 92..111) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (uruguayStickers?.get(i-92) ?: "uruguay"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Uruguay Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Portugal Team stickers
        for (i in 112..131) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (portugalStickers?.get(i-112) ?: "portugal"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Portugal Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Spain Team stickers
        for (i in 132..151) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (spainStickers?.get(i-132) ?: "spain"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Spain Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Morocco Team stickers
        for (i in 152..171) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (moroccoStickers?.get(i-152) ?: "morocco"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Morocco Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Iran Team stickers
        for (i in 172..191) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (iranStickers?.get(i-172) ?: "iran"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Iran Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed France Team stickers
        for (i in 192..211) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (franceStickers?.get(i-192) ?: "france"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "France Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Australia Team stickers
        for (i in 212..231) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (australiaStickers?.get(i-212) ?: "australia"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Australia Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Peru Team stickers
        for (i in 232..251) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (peruStickers?.get(i-232) ?: "peru"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Peru Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Denmark Team stickers
        for (i in 252..271) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (denmarkStickers?.get(i-252) ?: "denmark"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Denmark Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Argentina Team stickers
        for (i in 272..291) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (argentinaStickers?.get(i-272) ?: "argentina"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Argentina Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Iceland Team stickers
        for (i in 292..311) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (icelandStickers?.get(i-292) ?: "iceland"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Iceland Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Croatia Team stickers
        for (i in 312..331) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (croatiaStickers?.get(i-312) ?: "croatia"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Croatia Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Nigeria Team stickers
        for (i in 332..351) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (nigeriaStickers?.get(i-332) ?: "nigeria"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Nigeria Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Brazil Team stickers
        for (i in 352..371) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (brazilStickers?.get(i-352) ?: "brazil"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Brazil Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Switzerland Team stickers
        for (i in 372..391) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (switzerlandStickers?.get(i-372) ?: "switzerland"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Switzerland Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Costa Rica Team stickers
        for (i in 392..411) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (costaRicaStickers?.get(i-392) ?: "costa rica"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Costa Rica Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Serbia Team stickers
        for (i in 412..431) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (serbiaStickers?.get(i-412) ?: "serbia"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Serbia Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Germany Team stickers
        for (i in 432..451) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (germanyStickers?.get(i-432) ?: "germany"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Germany Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Mexico Team stickers
        for (i in 452..471) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (mexicoStickers?.get(i-452) ?: "mexico"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Mexico Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Sweden Team stickers
        for (i in 472..491) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (swedenStickers?.get(i-472) ?: "sweden"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Sweden Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Korea Republic Team stickers
        for (i in 492..511) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (koreaRepublicStickers?.get(i-492) ?: "korea republic"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Korea Republic Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Belgium Team stickers
        for (i in 512..531) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (belgiumStickers?.get(i-512) ?: "belgium"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Belgium Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Panama Team stickers
        for (i in 532..551) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (panamaStickers?.get(i-532) ?: "panama"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Panama Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Tunisia Team stickers
        for (i in 552..571) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (tunisiaStickers?.get(i-552) ?: "tunisia"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Tunisia Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed England Team stickers
        for (i in 572..591) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (englandStickers?.get(i-572) ?: "england"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "England Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Poland Team stickers
        for (i in 592..611) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (polandStickers?.get(i-592) ?: "poland"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Poland Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Senegal Team stickers
        for (i in 612..631) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (senegalStickers?.get(i-612) ?: "senegal"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Senegal Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Colombia Team stickers
        for (i in 632..651) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (colombiaStickers?.get(i-632) ?: "colombia"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Colombia Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Japan Team stickers
        for (i in 652..671) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (japanStickers?.get(i-652) ?: "japan"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Japan Team",
                    Sticker.COLUMN_QUANTITY to 0
            )
        }
        //Seed Legends stickers
        for (i in 672..681) {
            db.insert(Sticker.TABLE_NAME,
                    Sticker.COLUMN_NAME to (legendsStickers?.get(i-672) ?: "legends"),
                    Sticker.COLUMN_NUMBER to i,
                    Sticker.COLUMN_TYPE to "Legends",
                    Sticker.COLUMN_QUANTITY to 0
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