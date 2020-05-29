package com.vantrai.challengew4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.gson.Gson
import com.vantrai.challengew4.Fragment.ContentFragment
import com.vantrai.challengew4.model.DataCenter
import com.vantrai.challengew4.model.Movie
import com.vantrai.challengew4.model.NowPlayingMovie
import com.vantrai.challengew4.model.TopRateMovie
import com.vantrai.challengew4.room.AppDatabase
import kotlinx.android.synthetic.main.main_screen.*


class MainActivity : AppCompatActivity() {
    var gridPlay: Boolean = false
    var gridRating: Boolean = false
    var gridFavorite: Boolean = false
    var tabIndex: Int = 0
    private val LISTMOVE: String = "listMovie"
    private val GRIDLAYOUT: String = "gridLayout"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        setGridLayout(gridPlay)
        setFragmentTitle(tabIndex)
        setDataAndGridLayout()

        layoutBtn.setOnClickListener() {
            if (tabIndex == 0) {
                gridPlay = !gridPlay
                setGridLayout(gridPlay)
                setFragmentTitle(tabIndex)
                setDataAndGridLayout()
            } else if (tabIndex == 1) {
                gridRating = !gridRating
                setGridLayout(gridRating)
                setFragmentTitle(tabIndex)
                setDataAndGridLayout()
            } else if (tabIndex == 2) {
                gridFavorite = !gridFavorite
                setGridLayout(gridFavorite)
                setFragmentTitle(tabIndex)
                setDataAndGridLayout()
            }
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.tab_playing -> {
                    tabIndex = 0
                    setFragmentTitle(tabIndex)
                    setGridLayout(gridPlay)
                    setDataAndGridLayout()
                }
                R.id.tab_top_rating -> {
                    tabIndex = 1
                    setFragmentTitle(tabIndex)
                    setGridLayout(gridRating)
                    setDataAndGridLayout()
                }
                R.id.tab_favorite -> {
                    tabIndex = 2
                    setFragmentTitle(tabIndex)
                    setGridLayout(gridFavorite)
                    setDataAndGridLayout()
                }
            }
            true
        }
    }

    private fun setDataAndGridLayout() {
        val playingList = Gson().fromJson<NowPlayingMovie>(
            DataCenter.getNowPlayingMovieJson(),
            NowPlayingMovie::class.java
        )
        val topRating = Gson().fromJson<TopRateMovie>(
            DataCenter.getTopRateMovieJson(),
            TopRateMovie::class.java
        )
        val favoriteList = getListFavoriteMovie()
        val contentFragment = ContentFragment()
        var bundle = Bundle()
        when (tabIndex) {
            0 -> {
                bundle.putBoolean(GRIDLAYOUT, gridPlay)
                bundle.putParcelableArrayList(LISTMOVE, playingList.results)
            }
            1 -> {
                bundle.putBoolean(GRIDLAYOUT, gridRating)
                bundle.putParcelableArrayList(LISTMOVE, topRating.results)
            }
            2 -> {
                bundle.putBoolean(GRIDLAYOUT, gridFavorite)
                bundle.putParcelableArrayList(LISTMOVE, favoriteList)
            }
        }
        contentFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, contentFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setGridLayout(gridLayout: Boolean) {
        if (gridLayout) {
            layoutBtn.setImageResource(R.drawable.ic_grid)
        } else {
            layoutBtn.setImageResource(R.drawable.ic_list)
        }
    }

    private fun setFragmentTitle(tabIndex: Int) {
        if (tabIndex == 0) {
            fragmentTitle.text = "Play"
        } else if (tabIndex == 1) {
            fragmentTitle.text = "Rating"
        } else if (tabIndex == 2) {
            fragmentTitle.text = "Favorite"
        }
    }

    private fun getListFavoriteMovie(): ArrayList<Movie> {
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "mydatabase.db")
            .allowMainThreadQueries().build()
        var data = ArrayList<Movie>()
        data = db.movieDAO().getAll() as ArrayList<Movie>
        return data
    }
}