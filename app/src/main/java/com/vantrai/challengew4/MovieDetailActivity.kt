package com.vantrai.challengew4

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.squareup.picasso.Picasso
import com.vantrai.challengew4.model.Movie
import com.vantrai.challengew4.room.AppDatabase
import kotlinx.android.synthetic.main.movie_info_layout.*

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_info_layout)
        val movie = intent.getParcelableExtra(R.string.MOVIEDATA.toString()) as Movie
        val imageUrl: String = "https://image.tmdb.org/t/p/w500/" + movie.poster_path

        movieTitle.text = movie.title
        movieAdult.text =
            "Phù hợp: " + if (movie.adult) "Người lớn trên 18 tuổi." else "Mọi lứa tuổi."
        movieOverView.text = movie.overview
        movieRating.text = "Rating: " + movie.vote_average.toString()
        movieRelease.text = "Release: " + movie.release_date``````
        moviePopularity.text = "Viewer: " + movie.popularity.toString()
        movieLanguage.text = "Language: " + movie.original_language
        Picasso.get().load(imageUrl).into(movieImage)
        addToFavoriteListBtn.setOnClickListener() {
            val alertDialogBuilder = AlertDialog.Builder(this)
                .setTitle("Xác nhận")
                .setMessage("Bạn muôn thêm phim ${movie.title} vào danh sách yêu thích?")
                .setPositiveButton("Đồng ý") { dialog, which ->
                    addMovieToFavorite(movie)
                    Toast.makeText(
                        this,
                        "Successfully insert movie to Favorite List",
                        Toast.LENGTH_LONG
                    ).show()
                }
                .setNegativeButton("Hủy") { dialog, which ->
                    dialog.dismiss()
                }.show()
        }
    }

    private fun addMovieToFavorite(movie: Movie) {
        var db = Room.databaseBuilder(this, AppDatabase::class.java, "mydatabase.db")
            .allowMainThreadQueries().build()
        db.movieDAO().insert(movie);
    }
}