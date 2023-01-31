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
    private lateinit var hello: List<Book>

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
            testDB()
            listAdapter()
        }



    }


    suspend fun testDB() {


        //Insert
        Log.d("TAG", "*****     Inserting 3 Books     **********")
//        bookDao.insertBook(Book(0, "Java", "Alex"))
//        bookDao.insertBook(Book(0, "PHP", "Mike"))
//        bookDao.insertBook(Book(0, "Kotlin", "Amelia"))
        Log.d("TAG", "*****     Inserted 3 Books       **********")

        //Query
        val books = bookDao.getAllBooks()
        hello = books


        Log.d("TAG", "*****   ${books.size} books there *****")
        for (book in books) {
            Log.d("TAG", "id: ${book.id} name: ${book.name} author: ${book.author}")
        }

        val testUserId = bookDao.getBookByID(2)
        Log.d("TAG", "testDB: $testUserId -> ID: ${testUserId.id}")

    }

    fun listAdapter() {
        adapter = RecyclerViewAdapter(hello)
        bookList.adapter = adapter
    }

}