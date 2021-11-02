package com.mikle.calendarapplication.db

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikle.calendarapplication.BloknotActivity
import com.mikle.calendarapplication.R

class MyAdapter(ListMain: ArrayList<ListItem>, contextM: Context) : RecyclerView.Adapter<MyAdapter.MyHolder>() {
    var context = contextM
    var listArray = ListMain


    class MyHolder(itemView: View, contextViewHolder: Context) : RecyclerView.ViewHolder(itemView) {
        val context = contextViewHolder
        val tvTitle: TextView = itemView.findViewById(R.id.dataStart)
        val tvNote: TextView = itemView.findViewById(R.id.dataFinish)
        val tvUri : TextView = itemView.findViewById(R.id.nameNoteTitle)
        fun setData(item: ListItem){

            tvNote.text = item.contentNote
            tvTitle.text = item.title
            tvUri.text = item.uri

            itemView.setOnClickListener {

                val intentBloknot = Intent(context, BloknotActivity::class.java).apply {
                    putExtra(MyIntentConstants.INTENT_TITLE_KEY, item.title)
                    putExtra(MyIntentConstants.INTENT_NOTE_KEY, item.contentNote)
                    putExtra(MyIntentConstants.INTENT_URI_KEY, item.uri)
                }
                    context.startActivity(intentBloknot)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater =LayoutInflater.from(parent.context)
        return  MyHolder(inflater.inflate(R.layout.rc_item, parent, false), context)

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(listArray.get(position))
    }

    override fun getItemCount(): Int {

        return  listArray.size
    }

    fun updateAdapter(listItem: List<ListItem>){

        listArray.clear()
        listArray.addAll(listItem)
        notifyDataSetChanged()

    }
}