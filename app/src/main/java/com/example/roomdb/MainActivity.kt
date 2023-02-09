package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.roomdb.database.Book
import com.example.roomdb.database.BookDao
import com.example.roomdb.database.BookDatabase
import com.example.roomdb.recyclerview.RecyclerViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // ViewModel
    private lateinit var viewModel: MainViewModel

    //Coroutine Scope
    private val scope = CoroutineScope(Dispatchers.Main)

    //Recycler View variables
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var bookList: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bookList = findViewById(R.id.list)

        init()

        scope.launch {
            //Room DB has to be used in a Coroutine Scope on the IO thread
            viewModel.addData()

            viewModel.getData().collect {
                listAdapter(it)
            }

            viewModel.getBookByID(4).collect {
                Log.d("TAG", "onCreate: $it")
            }
        }
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    fun listAdapter(books: List<Book>) {
        Log.d("TAG", "listAdapter: Created List")
        adapter = RecyclerViewAdapter(books)
        bookList.adapter = adapter
    }

}

