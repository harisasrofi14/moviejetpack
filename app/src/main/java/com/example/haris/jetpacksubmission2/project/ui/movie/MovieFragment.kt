package com.example.haris.jetpacksubmission2.project.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.haris.jetpacksubmission2.R
import com.example.haris.jetpacksubmission2.project.ui.detailmovie.DetailMovieActivity
import com.example.haris.jetpacksubmission2.project.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()
            progress_bar.visibility = View.VISIBLE
            viewModel.getMovie().observe(viewLifecycleOwner, Observer { movies ->
                movieAdapter.setData(movies)
                progress_bar.visibility = View.GONE
            })
            with(rv_movie) {
                layoutManager = GridLayoutManager(context, 3)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
            movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback{
                override fun onItemClicked(data: MovieEntity) {
                    val intent = Intent(context, DetailMovieActivity::class.java).apply {
                        putExtra("movieId", data.id)
                    }
                    context?.startActivity(intent)
                }

            })

        }
    }
    
}