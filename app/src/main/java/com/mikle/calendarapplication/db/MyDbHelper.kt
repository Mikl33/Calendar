package com.mikle.calendarapplication.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.security.AccessControlContext
import java.sql.SQLClientInfoException

class MyDbHelper(context: Context) : SQLiteOpenHelper (context, MyDbNameClass.DATABASE_NAME,
    null, MyDbNameClass.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(MyDbNameClass.CREAT_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL(MyDbNameClass.SQL_DELETE_TABEL)
        onCreate(db)
    }

}