package com.nebulark.hikayatapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nebulark.hikayatapp.adapter.BooksAdapter
import com.nebulark.hikayatapp.models.BooksDataModel


class BooksFragment : Fragment() {
    private lateinit var view1: View
    private lateinit var mBooksRecyclerview: RecyclerView;
    private var booksList = ArrayList<BooksDataModel>()
    private lateinit var booksAdapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view1 = view

        init()


    }

    private fun init() {
        //getting recyclerview from xml
        mBooksRecyclerview = view1.findViewById(R.id.booksRecyclerview) as RecyclerView

        //adding a layoutmanager
        mBooksRecyclerview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)


        //crating an arraylist to store users using the data class user
        booksList = ArrayList<BooksDataModel>()
        //adding some dummy data to the list
        addBooksToList()
        //creating our adapter
        booksAdapter = BooksAdapter(booksList)

        //now adding the adapter to recyclerview
        mBooksRecyclerview.adapter = booksAdapter

    }

    private fun addBooksToList() {
        booksList.add(BooksDataModel("1.", "Book 1"))
        booksList.add(BooksDataModel("2.", "Book 2"))
        booksList.add(BooksDataModel("3.", "Book 3"))
        booksList.add(BooksDataModel("4.", "Book 4"))
    }


}