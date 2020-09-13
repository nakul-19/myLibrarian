package com.mylibrarian

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.record_layout.view.*

class RecordAdapter(val records : List<Record>) : RecyclerView.Adapter<RecordAdapter.RecordViewHolder>(){

    class RecordViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.record_layout,parent,false)
            )
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.view.bName.text = records[position].b_name
        holder.view.pName.text = records[position].p_name

        holder.view.setOnClickListener {
            if(records[position].type=="L")
            {
                val action = BooksGivenDirections.actionBooksGivenToAddLentBook()
                action.lBook = records[position]
                Navigation.findNavController(it).navigate(action)
            }
            if(records[position].type=="B")
            {
                val action = BooksTakenDirections.actionBooksTakenToAddBorrowedBook()
                action.bBook = records[position]
                Navigation.findNavController(it).navigate(action)
            }
            return@setOnClickListener
        }
    }
    override fun getItemCount(): Int = records.size
}