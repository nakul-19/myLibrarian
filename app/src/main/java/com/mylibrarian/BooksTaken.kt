package com.mylibrarian

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BooksTaken : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("taken","reached")
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.books_taken, container, false)
        val addButton = view.findViewById<FloatingActionButton>(R.id.add_borrowed_book)
        addButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_booksTaken_to_addBorrowedBook)
        }
        return view
    }
}
