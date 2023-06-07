package com.example.library

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.library.database.MyDatabase
import com.example.library.databinding.ResultLayoutBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding : ResultLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ResultLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bookPosition = intent.getIntExtra("resultKey", 0)
        val bookType = intent.getStringExtra("resultType")
        val myDatabase = MyDatabase(baseContext)
        val books = myDatabase.readBooks(bookType)
        binding.resultTitle.text = books[bookPosition].bookName
        binding.resultSubtitle.text = books[bookPosition].bookText
    }
}