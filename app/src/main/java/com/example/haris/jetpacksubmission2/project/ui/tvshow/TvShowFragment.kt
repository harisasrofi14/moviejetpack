package com.example.haris.jetpacksubmission2.project.ui.tvshow

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
import com.example.haris.jetpacksubmission2.project.ui.detailtvshow.DetailTvShowActivity
import com.example.haris.jetpacksubmission2.project.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv_show.*


class TvShowFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            val tvShowsAdapter = TvShowAdapter()
            progress_bar.visibility = View.VISIBLE
            viewModel.getTvShow().observe(viewLifecycleOwner, Observer { tvShow ->
                tvShowsAdapter.setData(tvShow)
                progress_bar.visibility = View.GONE
            })
            with(rv_tv_show) {
                layoutManager = GridLayoutManager(context, 3)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }
            tvShowsAdapter.setOnItemClickCallback(object : TvShowAdapter.OnItemClickCallback {
                override fun onItemClicked(data: TvShowEntity) {
                    val intent = Intent(context, DetailTvShowActivity::class.java).apply {
                        putExtra("tvId", data.id)
                    }
                    context?.startActivity(intent)
                }

            })
        }
    }
}