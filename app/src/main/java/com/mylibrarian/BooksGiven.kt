package com.mylibrarian

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BooksGiven : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.books_given, container, false)
        val addButton = view.findViewById<FloatingActionButton>(R.id.add_lent_book)
        addButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_booksGiven_to_addLentBook)
        }
        return view
    }
}