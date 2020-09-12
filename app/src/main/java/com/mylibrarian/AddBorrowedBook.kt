package com.mylibrarian

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
import androidx.room.CoroutinesRoom.Companion.execute
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.add_borrowed_book.*
import kotlinx.coroutines.launch

class AddBorrowedBook : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add_borrowed_book, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        add_borrowed_book.setOnClickListener {
            val personName = person_name.text.toString()
            val bookName = book_name.text.toString()
            if(bookName.isBlank()) {
                Toast.makeText(activity,"Book Name cannot be left blank!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(personName.isBlank()) {
                Toast.makeText(activity,"Name of lender cannot be left blank!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            launch {
                val rec = Record(bookName,personName,'B')
                context.let {
                    RecordDatabase(it!!).getRecordDao().addRecord(rec)
                    Toast.makeText(activity,"Entry saved!",Toast.LENGTH_SHORT).show()
                    val list = RecordDatabase(it).getRecordDao().getBorrowedBooks()
                    Log.i("insert",list.toString())
                }
            }
            findNavController().navigate(R.id.action_addBorrowedBook_to_booksTaken)
        }
    }

}