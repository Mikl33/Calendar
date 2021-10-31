package com.mikle.calendarapplication.db

import android.provider.BaseColumns

object MyDbNameClass : BaseColumns {                            //0    title       content
    const val TABLE_NAME = "my_table"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_CONTENT = "subtitle"
    const val COLUMN_IMAGE_URI = "uri"

    const val DATABASE_VERSION = 5
    const val DATABASE_NAME = "MyLessons.db"

    const val  CREAT_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_TITLE TEXT,$COLUMN_NAME_CONTENT TEXT,$COLUMN_IMAGE_URI TEXT)"

    const val SQL_DELETE_TABEL = "DROP TABLE IF EXISTS $TABLE_NAME"

}