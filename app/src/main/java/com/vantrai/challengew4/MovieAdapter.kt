package com.vantrai.challengew4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vantrai.challengew4.model.Movie

class MovieAdapter(val ctx: Context, val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieVH>() {

    class MovieVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage = itemView.findViewById<ImageView>(R.id.movieThumbIMG)
        val movieTitle = itemView.findViewById<TextView>(R.id.movieNameTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.movie_item, parent, false)
        return MovieVH(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        val movie = movies[position]
        val imageUrl: String = "https://image.tmdb.org/t/p/w500/" + movie.poster_path
        Picasso.get().load(imageUrl).into(holder.movieImage)
        holder.movieTitle.text = movie.title

        holder.itemView.setOnClickListener() {
            var intent = Intent(ctx, MovieDetailActivity::class.java)
            var bundle = Bundle()
            bundle.putParcelable(R.string.MOVIEDATA.toString(), movie)
            intent.putExtras(bundle)
            ctx.startActivity(intent)
        }
    }
}