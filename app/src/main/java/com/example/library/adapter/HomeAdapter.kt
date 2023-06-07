package com.example.library.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.library.databinding.ItemBookBinding
import com.example.library.ui.HomeItem
import com.example.library.ui.ItemLongClick

class HomeAdapter(
    private val bookList: ArrayList<String>,
    private val homeItem: HomeItem,
    private val longClick: ItemLongClick?
) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    lateinit var binding: ItemBookBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.itemBookTv.text = bookList[position]

        holder.itemView.setOnClickListener {
            homeItem.onClick(position)
        }

        holder.itemView.setOnLongClickListener {
            longClick?.onLongClick(position)
            true
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    class ViewHolder(iteView: View) : RecyclerView.ViewHolder(iteView) {

    }
}