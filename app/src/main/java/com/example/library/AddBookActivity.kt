package com.example.library

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.library.database.MyDatabase
import com.example.library.databinding.AddbookMainBinding
import com.example.library.model.Book

class AddBookActivity : AppCompatActivity() {
    lateinit var binding: AddbookMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddbookMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myDatabase = MyDatabase(baseContext);
        val bookTypes = resources.getStringArray(R.array.home_list)
        binding.spinner.adapter =
            ArrayAdapter(
                baseContext,
                android.R.layout.simple_list_item_1,
                bookTypes
            )
        binding.spinner.gravity = View.TEXT_ALIGNMENT_CENTER

        binding.qoshishBtn.setOnClickListener {
            if (binding.bookName.text.isNotEmpty() && binding.bookText.text.isNotEmpty()) {
                val book = Book(
                    bookTypes[binding.spinner.selectedItemPosition],
                    binding.bookName.text.toString(),
                    binding.bookText.text.toString()
                )
                myDatabase.insertBook(book)
                Toast.makeText(baseContext, "Kitob qo'shildi", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(baseContext, "Ma'lumot to'ldiring...", Toast.LENGTH_SHORT).show()
            }
        }
    }
}