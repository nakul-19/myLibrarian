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
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddBorrowedBook : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.add_borrowed_book, container, false)
        val addButton = view.findViewById<FloatingActionButton>(R.id.add_borrowed_book)

        addButton.setOnClickListener {
            val personName = view.findViewById<EditText>(R.id.person_name)
            val bookName = view.findViewById<EditText>(R.id.book_name)
            if(bookName.text.isBlank()) {
                Toast.makeText(activity,"Book Name cannot be left blank!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(personName.text.isBlank()) {
                Toast.makeText(activity,"Name of lender cannot be left blank!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            view.findNavController().navigate(R.id.action_addBorrowedBook_to_booksTaken)
        }
        return view
    }
}