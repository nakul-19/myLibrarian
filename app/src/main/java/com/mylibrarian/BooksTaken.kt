package com.mylibrarian

import android.icu.text.AlphabeticIndex
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.books_taken.*
import kotlinx.coroutines.launch

class BooksTaken : BaseFragment() {

    lateinit var recordAdapter: RecordAdapter
    private var records: List<Record> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("taken","reached")
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.books_taken, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.taken_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recordAdapter = RecordAdapter(records)
        recyclerView.adapter = recordAdapter
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addBorrowedBook.setOnClickListener {
            findNavController().navigate(R.id.action_booksTaken_to_addBorrowedBook)
        }

        launch {
            context?.let {
                records = RecordDatabase(it).getRecordDao().getBorrowedBooks()
                recordAdapter.notifyDataSetChanged()
                Log.d("Records", records.toString())
            }
        }
    }
}