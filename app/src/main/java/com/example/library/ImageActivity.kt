package com.example.library

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.library.databinding.ImageActivityLayoutBinding

class ImageActivity : AppCompatActivity() {

    lateinit var binding: ImageActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ImageActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageActivityImage.setImageDrawable(resources.getDrawable(intent.getIntExtra("imageKey", R.drawable.img_7)))
    }
}