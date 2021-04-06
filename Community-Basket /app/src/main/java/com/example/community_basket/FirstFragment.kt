package com.example.community_basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.community_basket.databinding.FragmentFirstBinding

class FirstFragment : Fragment(R.layout.fragment_first), OnItemClickListener {
    private val movieList = generateDummyList(30)
    private val adapter = CustomAdapter(movieList, this)
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    companion object {
        var MOVIE_TITLE = "movie title"
        var MOVIE = "movie"
    }

    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.insertButton.setOnClickListener {
            val index = 0
            val newItem = MovieModel("New item", "$index", R.drawable.iv_picture)

            movieList.add(index, newItem)
            adapter.notifyItemInserted(index)
            binding.recyclerView.layoutManager?.scrollToPosition(0)
        }
        binding.removeButton.setOnClickListener {
            val index = 0
            movieList.removeAt(index)
            adapter.notifyItemRemoved(index)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun generateDummyList(size: Int): MutableList<MovieModel> {
        val list: MutableList<MovieModel> = ArrayList()

        for (i in 0 until size) {
            val imageId = when (i % 3) {
                0 -> R.drawable.iv_picture
                1 -> R.drawable.iv_picture
                else -> R.drawable.iv_picture
            }
            val item = MovieModel("Item $i", "$i", imageId)
            list += item
        }
        return list
    }

    override fun onItemClick(item: MovieModel) {
//        Toast.makeText(getContext(), "${item.title} clicked", Toast.LENGTH_SHORT).show()

        val bundle = Bundle()
        bundle.putString(MOVIE_TITLE, item.title)
        bundle.putParcelable(MOVIE, item)

        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        val fragment = SecondFragment()
        fragment.arguments = bundle
        fragmentTransaction.replace(R.id.fragment_container, fragment)
            .addToBackStack(null)

        fragmentTransaction.commit()
    }
}