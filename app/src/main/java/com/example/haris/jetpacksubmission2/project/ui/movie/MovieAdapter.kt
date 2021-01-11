package com.example.haris.jetpacksubmission2.project.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.haris.jetpacksubmission2.R
import kotlinx.android.synthetic.main.item_grid_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.GridViewHolder>() {
    private val mData = ArrayList<MovieEntity>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(items: List<MovieEntity>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): MovieAdapter.GridViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_grid_movie, viewGroup, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.GridViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieEntity) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + movie.posterPath)
                    .fitCenter()
                    .into(iv_poster)
                tv_title.text = movie.title
                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(movie)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: MovieEntity)
    }
}