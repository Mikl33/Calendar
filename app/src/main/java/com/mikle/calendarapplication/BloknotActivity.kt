package com.mikle.calendarapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import kotlinx.android.synthetic.main.activity_bloknot.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.mikle.calendarapplication.db.MyDbMAnager
import com.mikle.calendarapplication.db.MyIntentConstants


class BloknotActivity : AppCompatActivity() {

    val imageRequestCode = 100
    var tempImageUri = "empty"
    val myDbMAnager = MyDbMAnager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bloknot)

        getMyIntents()


      //  editTextZagolovok.setText(mesage)


     //   var editTextDateNote = findViewById<TextView>(R.id.editTextZagolovok)
       //     editTextDateNote.text = mesage


    }


    override fun onDestroy(){
        super.onDestroy()
        myDbMAnager.closeDb()

    }

    override fun onResume() {
        super.onResume()
        myDbMAnager.openDb()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == imageRequestCode){

            imageView.setImageURI(data?.data)
            tempImageUri = data?.data.toString()

        }
    }

    fun addClikcImage(view: View) {

        findViewById<ConstraintLayout>(R.id.ImageLayout).visibility = View.VISIBLE
        findViewById<ImageButton>(R.id.btAddImageClick).visibility = View.GONE
    }

    fun deletImageNote(view: View) {
        findViewById<ConstraintLayout>(R.id.ImageLayout).visibility = View.GONE
        findViewById<ImageButton>(R.id.btAddImageClick).visibility = View.VISIBLE

    }

    fun onClickSave(view: View){

        val myTitel = editTextZagolovok.text.toString()
        val myNote = editTextNoteContent.text.toString()
      //  textView3.text = ""
       if (myTitel == "" || myNote == ""){
           Toast.makeText(this,"No get Input< Please try again", Toast.LENGTH_LONG).show()
       } else {
           // myDbMAnager.openDb()
            myDbMAnager.insertToDb(myTitel, myNote, tempImageUri.toString())
            finish()
     /*   val dataList = myDbMAnager.readDbData()
        for (item in dataList ) {
            textView3.append(item)
            textView3.append("\n")
        }*/

        }

    }

    fun onClickChooseImage(view: View){

      val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        startActivityForResult(intent, imageRequestCode)
    }

    fun getMyIntents(){
        val  i = intent
        if (i != null){
        Log.d("MyLog", "Data putExtra" + i.getStringExtra(MyIntentConstants.INTENT_URI_KEY) )
          if (intent.getStringExtra("key") !== null){
            //  var mesage: String = ""
           // var mesage = intent.getStringExtra("key")
              editTextZagolovok.setText(intent.getStringExtra("key"))

          } else if (i.getStringExtra(MyIntentConstants.INTENT_TITLE_KEY) !== "" ) {
              ImageLayout.visibility = View.VISIBLE
              editTextZagolovok.setText(i.getStringExtra(MyIntentConstants.INTENT_TITLE_KEY))
                editTextNoteContent.setText(i.getStringExtra(MyIntentConstants.INTENT_NOTE_KEY))
              imageView.setImageURI(Uri.parse(i.getStringExtra(MyIntentConstants.INTENT_URI_KEY)))
              btAddImageClick.visibility = View.GONE


              if (i.getStringExtra(MyIntentConstants.INTENT_URI_KEY) == "empty"){
                  ImageLayout.visibility = View.GONE
                  //  imageButtonEdite.visibility = View.GONE
                 // imageDelet.visibility = View.GONE
                  btAddImageClick.visibility = View.VISIBLE


              }

          }
        }
    }


}