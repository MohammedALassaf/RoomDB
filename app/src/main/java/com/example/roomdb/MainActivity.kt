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
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private lateinit var bookDao: BookDao
    private val scope = CoroutineScope(Dispatchers.IO)
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var bookList: RecyclerView
    private lateinit var hello: List<Book>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bookList = findViewById(R.id.list)

        val db = Room.databaseBuilder(
            applicationContext,
            BookDatabase::class.java, "book_database"
        ).build()
        bookDao = db.bookDao()

        scope.launch {
            testDB()
            listAdapter()
        }
    }
    git remote add origin https://github.com/MohammedALassaf/RoomDB.git
    suspend fun testDB() {


        //Insert
        Log.i("MyTAG", "*****     Inserting 3 Books     **********")
        bookDao.insertBook(Book(0, "Java", "Alex"))
        bookDao.insertBook(Book(0, "PHP", "Mike"))
        bookDao.insertBook(Book(0, "Kotlin", "Amelia"))
        Log.i("MyTAG", "*****     Inserted 3 Books       **********")

        //Query
        val books = bookDao.getAllBooks()
        hello = books
        Log.i("MyTAG", "*****   ${books.size} books there *****")
        for (book in books) {
            Log.i("MyTAG", "id: ${book.id} name: ${book.name} author: ${book.author}")
        }

    }

    suspend fun listAdapter() {
        adapter = RecyclerViewAdapter(hello)

        bookList.adapter = adapter
    }

}