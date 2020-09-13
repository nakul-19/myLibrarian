package com.mylibrarian

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.books_given.*
import kotlinx.android.synthetic.main.books_taken.*
import kotlinx.android.synthetic.main.books_taken.addBorrowedBook
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class BooksTaken : BaseFragment() {

    lateinit var recordAdapter: RecordAdapter
    var records: ArrayList<Record> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("taken","reached")
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
            val action = BooksTakenDirections.actionBooksTakenToAddBorrowedBook()
            action.bBook = null
            findNavController(it).navigate(action)
        }

        launch {
            context?.let {
                records.clear()
                records.addAll(RecordDatabase(it).getRecordDao().getBorrowedBooks())
                recordAdapter.notifyDataSetChanged()
                Log.d("Records", records.toString())
            }
            Log.d("Records", records.toString())
        }
    }
}