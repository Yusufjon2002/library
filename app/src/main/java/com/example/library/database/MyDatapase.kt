package com.example.library.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.library.model.Book

class MyDatabase(val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "create table $TABLE_NAME (" +
                "id integer primary key, " +
                "book_type text not null, " +
                "book_name text not null, " +
                "book_text text not null)"
        db?.execSQL(query)



    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertBook(book: Book) {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put("book_type", book.bookType)
        contentValues.put("book_name", book.bookName)
        contentValues.put("book_text", book.bookText)
        db.insert(TABLE_NAME, null, contentValues)
    }

    fun     readBooksName(bookType: String?): ArrayList<String> {
        val db = readableDatabase
        val selectQuery = "select * from $TABLE_NAME where book_type = \"$bookType\" "
        val cursor: Cursor = db.rawQuery(selectQuery, arrayOf())
        val bookList = ArrayList<String>()
        while (cursor.moveToNext()) {
            bookList.add(cursor.getString(2))
        }
        return bookList
    }

    fun readBooks(bookType: String?): ArrayList<Book> {
        val db = readableDatabase
        val selectQuery = "select * from $TABLE_NAME where book_type = \"$bookType\" "
        val cursor: Cursor = db.rawQuery(selectQuery, arrayOf())
        val bookList = ArrayList<Book>()
        while (cursor.moveToNext()) {
            bookList.add(
                Book(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                )
            )
        }
        return bookList
    }

    fun readBooks(): ArrayList<Book> {
        val db = readableDatabase
        val selectQuery = "select * from $TABLE_NAME"
        val cursor: Cursor = db.rawQuery(selectQuery, arrayOf())
        val bookList = ArrayList<Book>()
        while (cursor.moveToNext()) {
            bookList.add(
                Book(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                )
            )
        }
        return bookList
    }

    fun deleteBook(bookName: String): Int {
        val db = writableDatabase
        return db.delete(TABLE_NAME, "book_name=?", arrayOf(bookName))
//        return db.delete(TABLE_NAME, "id=?", arrayOf("$bookId"))
    }

    companion object {
        const val TABLE_NAME = "BOOKS"
        const val DATABASE_NAME = "My_Database"
        const val DATABASE_VERSION = 1
    }


}