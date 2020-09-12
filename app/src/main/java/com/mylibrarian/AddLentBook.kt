package com.mylibrarian

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.add_lent_book.*
import kotlinx.coroutines.launch

class AddLentBook : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_lent_book, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        add_lent_book.setOnClickListener {
            val personName = person_name1.text.toString()
            val bookName = book_name1.text.toString()
            if(bookName.isBlank()) {
                Toast.makeText(activity,"Book Name cannot be left blank!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(personName.isBlank()) {
                Toast.makeText(activity,"Name of borrower cannot be left blank!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            launch {
                val rec = Record(bookName,personName,'L')
                context?.let {
                    RecordDatabase(it).getRecordDao().addRecord(rec)
                    val list = RecordDatabase(it).getRecordDao().getBorrowedBooks()
                    Log.i("insert",list.toString())
                    Toast.makeText(activity,"Entry saved!",Toast.LENGTH_SHORT).show()
                }
            }
            findNavController().navigate(R.id.action_addLentBook_to_booksGiven)
        }
    }

}