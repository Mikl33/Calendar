package com.mikle.calendarapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mikle.calendarapplication.Model.UserNoteModel
import com.mikle.calendarapplication.R
import kotlinx.android.synthetic.main.item_note_layout.view.*

class MyJSONAdapter(context: Context, val items: ArrayList<UserNoteModel>):
    RecyclerView.Adapter<MyJSONAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_note_layout,
                parent,false
            )

        )
    }

    override fun onBindViewHolder(holder: MyJSONAdapter, position: Int) {
        val item = items.get(position)

        holder.

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvId = view.tvID
    }
}