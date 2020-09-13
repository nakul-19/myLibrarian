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
import kotlinx.android.synthetic.main.add_borrowed_book.*
import kotlinx.android.synthetic.main.add_lent_book.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class AddLentBook : BaseFragment() {

    var record: Record? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_lent_book, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            record = AddLentBookArgs.fromBundle(it).lBook
            if(record!=null) {
                person_name1.setText(record?.p_name)
                book_name1.setText(record?.b_name)
            }
        }

        deleteL.setOnClickListener {
            if(record==null){
                Toast.makeText(activity,"This record already doesn't exist!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            MainScope().launch {
                context?.let {
                    val rec = record!!
                    RecordDatabase(it).getRecordDao().delete(rec)
                }
            }
            findNavController().navigate(R.id.action_addLentBook_to_booksGiven)
        }

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

            MainScope().launch {

                context?.let {
                    val rec = Record(bookName,personName,"L")
                    if(record==null)
                    RecordDatabase(it).getRecordDao().addRecord(rec)
                    else{
                        rec.id = record!!.id
                        RecordDatabase(it).getRecordDao().update(rec)
                    }
                    val list = RecordDatabase(it).getRecordDao().getBooks()
                    Log.i("insert",list.toString())
//                    Toast.makeText(activity,"Entry saved!",Toast.LENGTH_SHORT).show()
                }
            }
            findNavController().navigate(R.id.action_addLentBook_to_booksGiven)
        }
    }

}