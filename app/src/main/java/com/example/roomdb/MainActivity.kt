package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.roomdb.database.Book
import com.example.roomdb.database.BookDao
import com.example.roomdb.database.BookDatabase
import com.example.roomdb.recyclerview.RecyclerViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    //Coroutine Scope
    private val scope = CoroutineScope(Dispatchers.IO)

    //Room variables
    private lateinit var bookDao: BookDao

    //Recycler View variables
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var bookList: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bookList = findViewById(R.id.list)

        // Initializing Room DB
        val db = Room.databaseBuilder(
            applicationContext,
            BookDatabase::class.java, "book_database"
        ).build()

        //Initializing Dao -> Data Access Object
        bookDao = db.bookDao()


        scope.launch {
            //Room DB has to be used in a Coroutine Scope on the IO thread
            addData()
            getData()
        }


    }


    suspend fun addData() {
        //Insert
        Log.d("TAG", "*****     Inserting 3 Books     **********")
        bookDao.insertBook(Book(0, "Java", "Alex"))
        bookDao.insertBook(Book(0, "PHP", "Mike"))
        bookDao.insertBook(Book(0, "Kotlin", "Amelia"))
        Log.d("TAG", "*****     Inserted 3 Books       **********")

    }

    private fun getData() {
        listAdapter(bookDao.getAllBooks())
    }

    private fun getBookByID(id: Int): Book = bookDao.getBookByID(id)

    fun listAdapter(books: List<Book>) {
        Log.d("TAG", "listAdapter: Created List")
        adapter = RecyclerViewAdapter(books)
        bookList.adapter = adapter
    }

}

