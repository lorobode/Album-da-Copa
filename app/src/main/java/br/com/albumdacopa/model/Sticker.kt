package br.com.albumdacopa.model

/**
 * Created by Nichollas on 05/04/2018.
 */
data class Sticker(val id: Int, val name: String, val number: Int, val type: String) {
    companion object {
        val TABLE_NAME = "Sticker"
        val COLUMN_NAME = "name"
        val COLUMN_NUMBER = "number"
        val COLUMN_TYPE = "type"
    }
}