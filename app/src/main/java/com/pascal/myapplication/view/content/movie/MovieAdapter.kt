package com.pascal.myapplication.view.content.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.pascal.myapplication.R
import com.pascal.myapplication.model.ResultsItem
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val data: List<ResultsItem?>?,
                   private val itemClick: OnClickListener
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.bind(item)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ResultsItem?) {
            view.list_nama.text = item?.name

            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500/${item?.posterPath}")
                .apply(
                    RequestOptions()
                        .override(200,200)
                        .placeholder(R.drawable.ic_image)
                        .error(R.drawable.ic_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH))
                .into(itemView.list_image)

            view.setOnClickListener {
                itemClick.detail(item)
            }
        }
    }

    interface OnClickListener {
        fun detail(item: ResultsItem?)
    }
}