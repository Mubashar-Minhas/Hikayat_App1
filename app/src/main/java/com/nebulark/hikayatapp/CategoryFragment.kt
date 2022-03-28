package com.nebulark.hikayatapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nebulark.hikayatapp.adapter.BooksAdapter
import com.nebulark.hikayatapp.models.BooksDataModel




class CategoryFragment : Fragment() {
    private lateinit var view1: View
    private lateinit var mCategoriesRecyclerview: RecyclerView;
    private var booksList = ArrayList<BooksDataModel>()
    private lateinit var booksAdapter: BooksAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view1 = view

        init()


    }

    private fun init() {

        //getting recyclerview from xml
        mCategoriesRecyclerview = view1.findViewById(R.id.categoriesRecyclerview) as RecyclerView

        //adding a layoutmanager
        mCategoriesRecyclerview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)


        //crating an arraylist to store users using the data class user
        booksList = ArrayList<BooksDataModel>()
        //adding some dummy data to the list
        addBooksToList()
        //creating our adapter
        booksAdapter = BooksAdapter(booksList)

        //now adding the adapter to recyclerview
        mCategoriesRecyclerview.adapter = booksAdapter

    }

    private fun addBooksToList() {
        booksList.add(BooksDataModel("1.", "Category 1"))
        booksList.add(BooksDataModel("2.", "Category 2"))
        booksList.add(BooksDataModel("3.", "Category 3"))
        booksList.add(BooksDataModel("4.", "Category 4"))
    }



}