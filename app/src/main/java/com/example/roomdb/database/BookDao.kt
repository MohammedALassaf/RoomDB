package com.example.roomdb.database

import androidx.room.*


@Dao
interface BookDao {

    //Insert Query is built in. Just use the Annotation "@Insert"
    @Insert
    //The suspended fun "insertBook" will be used to insert into the DB
    suspend fun insertBook(book: Book)


    //We can also add our own Queries
    @Query("SELECT * FROM books_table")
    fun getAllBooks(): List<Book>

    //We can also pass parameters to the function to use in the Query
    @Query("SELECT * FROM books_table WHERE id = :uid")
    suspend fun getBookByID(uid: Int): Book

    //Update Query is built in. Just use the Annotation "@Update"
    @Update
    suspend fun updateBook(book: Book)


    //Delete Query is built in. Just use the Annotation "@Delete"
    @Delete
    suspend fun deleteBook(book: Book)
}