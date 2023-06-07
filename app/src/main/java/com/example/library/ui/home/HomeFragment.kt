package com.example.library.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.library.BookActivity
import com.example.library.R
import com.example.library.adapter.HomeAdapter
import com.example.library.database.MyDatabase
import com.example.library.databinding.FragmentHomeBinding
import com.example.library.model.Book
import com.example.library.ui.HomeItem

class HomeFragment : Fragment(), HomeItem {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var myBooks = ArrayList<Book>()
    var books = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val booksNames = requireActivity().resources.getStringArray(R.array.home_list)
        books.clear()
        books.addAll(booksNames)
        var homeItem: HomeItem = this
        binding.recyclerView.adapter = HomeAdapter(books, homeItem, null)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        val myDatabase = MyDatabase(requireActivity())


        myBooks.clear()
        myBooks = myDatabase.readBooks()

        for (book in myBooks) {
            println("type: ${book.bookType}")
            println("name: ${book.bookName}")
            println("text: ${book.bookText}")
            println()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(position: Int) {
        Toast.makeText(requireActivity(), "Clicked: ${books[position]}", Toast.LENGTH_SHORT).show()
        val intent = Intent(requireActivity(), BookActivity::class.java)
        intent.putExtra("bookKey", books[position])
        startActivity(intent)
    }


}