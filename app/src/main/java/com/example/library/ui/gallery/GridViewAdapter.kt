package com.example.library.ui.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.library.R
import com.example.library.databinding.ItemGridViewBinding

class GridViewAdapter(private val context: Context, private val imageList: ArrayList<Int>) :
    BaseAdapter() {
    lateinit var binding: ItemGridViewBinding
    override fun getCount(): Int {
        return imageList.size
    }

    override fun getItem(position: Int): Any {
        return imageList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        binding = ItemGridViewBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        binding.itemGridImage.setImageDrawable(context.resources.getDrawable(imageList[position]))
        return binding.root
    }

}
