package com.mylibrarian

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.add_lent_book.*
import kotlinx.android.synthetic.main.books_given.*
import kotlinx.coroutines.launch

class BooksGiven : BaseFragment() {

    lateinit var recordAdapter: RecordAdapter
    var records: List<Record> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.books_given, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.given_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recordAdapter = RecordAdapter(records)
        recyclerView.adapter = recordAdapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addLentBook.setOnClickListener {
            findNavController().navigate(R.id.action_booksGiven_to_addLentBook)
        }

        launch {
            context?.let {
                records = RecordDatabase(it).getRecordDao().getLentBooks()
                recordAdapter.notifyDataSetChanged()
                Log.d("Records", records.toString())
            }
            Log.d("Records", records.toString())
        }
    }
}