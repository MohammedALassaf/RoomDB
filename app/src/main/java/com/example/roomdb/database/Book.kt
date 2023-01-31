package com.example.roomdb.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "books_table")
data class Book(

    //Primary key will be auto generated
    @PrimaryKey(autoGenerate = true)
    var id: Int,


    //Column name will be "name", same as the var name
    var name: String,


    //Column name will be "published_author"
    @ColumnInfo(name = "published_author")
    var author: String
)