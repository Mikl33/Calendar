package com.mikle.calendarapplication.db


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.icu.text.CaseMap

class MyDbMAnager(context: Context) {
    val mayDbHelper = MyDbHelper(context)
    var db: SQLiteDatabase? = null

     fun openDb(){
         db = mayDbHelper.writableDatabase
     }

    fun insertToDb(title: String, content: String , uri: String){

        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_TITLE, title)
            put(MyDbNameClass.COLUMN_NAME_CONTENT, content)
            put(MyDbNameClass.COLUMN_IMAGE_URI, uri)

        }
        db?.insert(MyDbNameClass.TABLE_NAME, null, values)

    }



    fun readDbData() : ArrayList<ListItem> {
        val dataList = ArrayList<ListItem>()
        val cursor = db?.query(
            MyDbNameClass.TABLE_NAME, null, null, null,
            null, null, null
        )


            while (cursor?.moveToNext()!!) {
                val dataTitle = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE))
                val dataContent = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_CONTENT))
                val dataUris = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_IMAGE_URI))
                val item = ListItem()

                item.title = dataTitle
                item.contentNote = dataContent
                item.uri = dataUris

                dataList.add(item)
            }
            cursor.close()
            return dataList
    }
    fun closeDb(){
        mayDbHelper.close()
    }
}

