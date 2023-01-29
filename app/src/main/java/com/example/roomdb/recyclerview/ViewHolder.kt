package com.example.roomdb.recyclerview

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.R
import com.example.roomdb.database.Book

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val name = view.findViewById<TextView>(R.id.name)
    private val author = view.findViewById<TextView>(R.id.author)
    private val root = view.findViewById<ConstraintLayout>(R.id.root)

    fun bind(book: Book){
        name.text = book.name
        author.text = book.author
        root.setOnContextClickListener {
            Log.d("TAG", "bind: ${book.id}")
            true
        }
    }
}