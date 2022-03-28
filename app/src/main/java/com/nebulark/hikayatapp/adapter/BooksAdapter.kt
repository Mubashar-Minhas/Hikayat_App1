package com.nebulark.hikayatapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.nebulark.hikayatapp.R
import com.nebulark.hikayatapp.adapter.BooksAdapter.ViewHolder
import com.nebulark.hikayatapp.models.BooksDataModel

class BooksAdapter(private val booksList: ArrayList<BooksDataModel>) : RecyclerView.Adapter<BooksAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.books_list, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(booksList [position])
    }

    override fun getItemCount(): Int {
        return booksList.size
    }


    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(booksList: BooksDataModel) {
            val textViewSerialNo = itemView.findViewById(R.id.bookNo) as MaterialTextView
            val textViewBookName = itemView.findViewById(R.id.bookName) as MaterialTextView
            textViewSerialNo.text = booksList.serialNo
            textViewBookName.text = booksList.bookName
        }



    }




}


