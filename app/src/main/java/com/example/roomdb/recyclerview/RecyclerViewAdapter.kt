package com.example.roomdb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.R
import com.example.roomdb.database.Book

class RecyclerViewAdapter(
    private val books: List<Book>
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context).inflate(R.layout.book_item_list, parent, false)

        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        if (holder is ViewHolder) holder.bind(book)
    }

    override fun getItemCount(): Int = books.size
}