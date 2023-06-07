package com.example.library.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.library.ImageActivity
import com.example.library.R
import com.example.library.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {
    private lateinit var binding: FragmentGalleryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val imageList = arrayListOf(
            R.drawable.img,
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3,
            R.drawable.img_4,
            R.drawable.img_5,
            R.drawable.img_5,
            R.drawable.img_7,
            R.drawable.img_8,
            R.drawable.img_9,
        )
        binding.gridView.adapter = GridViewAdapter(requireActivity(), imageList)
        binding.gridView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(requireActivity(), ImageActivity::class.java)
            intent.putExtra("imageKey", imageList[position])
            startActivity(intent)
        }



        return binding.root
    }
}