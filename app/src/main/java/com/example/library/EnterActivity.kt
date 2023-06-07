package com.example.library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.library.databinding.ActivityEnterBinding

class EnterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityEnterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.enterBtn.setOnClickListener {
            println("enterBtn clicked...")
            startActivity(Intent(baseContext,MainActivity::class.java))
            finish()
        }
    }
}