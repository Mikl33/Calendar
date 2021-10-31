package com.mikle.calendarapplication

import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikle.calendarapplication.db.MyAdapter
import com.mikle.calendarapplication.db.MyDbMAnager
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    var myAdapter = MyAdapter(ArrayList(), this)

    val myDbMAnager = MyDbMAnager(this)
    private var datePick = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
       // val button = findViewById<Button>(R.id.button)
        val calendarView = findViewById<CalendarView>(R.id.calendarView)


        calendarView.setOnDateChangeListener {view, year, month, dayOfMonth ->
            datePick =  "$dayOfMonth.${month + 1}.$year"

        }

    }


    override fun onDestroy(){
        super.onDestroy()
        myDbMAnager.closeDb()
    }

    override fun onResume() {
        super.onResume()
        myDbMAnager.openDb()
       fillAdapter()
    }

    fun onClikNotes(view: View) {
        if (datePick !== null){
        var intentA = Intent(this, BloknotActivity::class.java)
        intentA.putExtra("key", "$datePick")
    // startActivityForResult(intent,100)
        startActivity(intentA)
        } else{
           var intentB = Intent(this, BloknotActivity::class.java)
            startActivity(intentB)
        }
    }

    fun init(){

        rcView.layoutManager = LinearLayoutManager(this)
        rcView.adapter = myAdapter

    }

    fun fillAdapter(){

        myAdapter.updateAdapter(myDbMAnager.readDbData())

    }




}