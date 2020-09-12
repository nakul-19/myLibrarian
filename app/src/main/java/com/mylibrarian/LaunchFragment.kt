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

class LaunchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_launch, container, false)
        val lentButton = view.findViewById<Button>(R.id.lent)
        val borrowedButton = view.findViewById<Button>(R.id.borrowed)
        Log.i("launchscreen","reached")
        lentButton.setOnClickListener {
            Log.i("launchscreen","lent clicked")
            view.findNavController().navigate(R.id.action_launchFragment_to_booksGiven)
        }
        borrowedButton.setOnClickListener {
            Log.i("launchscreen","borrow cicked")
            view.findNavController().navigate(R.id.action_launchFragment_to_booksTaken)
        }
        return view
    }
}