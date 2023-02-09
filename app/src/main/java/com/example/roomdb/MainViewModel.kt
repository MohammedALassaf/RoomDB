package com.example.roomdb

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.roomdb.database.Book
import com.example.roomdb.database.BookDao
import com.example.roomdb.database.BookDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    //Room variables
    private var bookDao: BookDao

    init {
        // Initializing Room DB
        val db = Room.databaseBuilder(
            application,
            BookDatabase::class.java, "book_database"
        ).build()

        //Initializing Dao -> Data Access Object
        bookDao = db.bookDao()
    }

    suspend fun addData() = withContext(Dispatchers.IO) {
        //Insert
        Log.d("TAG", "*****     Inserting 3 Books     **********")
        bookDao.insertBook(Book(0, "Java", "Alex"))
        bookDao.insertBook(Book(0, "PHP", "Mike"))
        bookDao.insertBook(Book(0, "Kotlin", "Amelia"))
        Log.d("TAG", "*****     Inserted 3 Books       **********")
    }

    fun getData() = flow {
        emit(bookDao.getAllBooks())
    }.flowOn(Dispatchers.IO)

    fun getBookByID(id: Int) = flow {
        emit(bookDao.getBookByID(id))
    }.flowOn(Dispatchers.IO)
}