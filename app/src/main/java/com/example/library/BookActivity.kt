package com.example.library

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.library.adapter.HomeAdapter
import com.example.library.database.MyDatabase
import com.example.library.databinding.BookActivityLayoutBinding
import com.example.library.ui.HomeItem
import com.example.library.ui.ItemLongClick

class BookActivity : AppCompatActivity(), HomeItem, ItemLongClick {
    private lateinit var binding: BookActivityLayoutBinding
    private var bookType = ""
    private var myBooks = ArrayList<String>()
    private var myBooks1= ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BookActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myDatabase = MyDatabase(this)
        bookType = intent.getStringExtra("bookKey")!!
        myBooks = myDatabase.readBooksName(bookType)



        val homeItem: HomeItem = this
        val longClick: ItemLongClick = this
        binding.bookRecyclerView.adapter = HomeAdapter(myBooks, homeItem, longClick)
        binding.bookRecyclerView.adapter=HomeAdapter(myBooks1,homeItem, longClick)
        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(position: Int) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("resultKey", position)
        intent.putExtra("resultType", bookType)
        startActivity(intent)
    }


    override fun onLongClick(position: Int) {
        val dialog = myDialog(position)
        dialog.show()
    }

    private fun myDialog(position: Int): Dialog {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_item)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.findViewById<Button>(R.id.dialog_yes).setOnClickListener {
            Toast.makeText(baseContext, "${myBooks[position]} o'chirildi", Toast.LENGTH_SHORT)
                .show()
            val myDatabase = MyDatabase(this)
            myDatabase.deleteBook(myBooks[position])
            val homeItem: HomeItem = this
            val longClick: ItemLongClick = this
            myBooks = myDatabase.readBooksName(bookType)
            binding.bookRecyclerView.adapter = HomeAdapter(myBooks, homeItem, longClick)
            binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
            }

        dialog.findViewById<Button>(R.id.dialog_no).setOnClickListener {
            dialog.dismiss()
        }
        return dialog
    }

}