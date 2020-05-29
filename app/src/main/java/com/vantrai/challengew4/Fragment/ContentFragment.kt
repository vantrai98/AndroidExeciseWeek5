package com.vantrai.challengew4.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vantrai.challengew4.MovieAdapter
import com.vantrai.challengew4.R
import com.vantrai.challengew4.model.Movie
import kotlinx.android.synthetic.main.content.*

class ContentFragment : Fragment() {
    private val LISTMOVE: String = "listMovie"
    private val GRIDLAYOUT: String = "gridLayout"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.get(LISTMOVE) as ArrayList<Movie>
        val gridLayout = arguments?.get(GRIDLAYOUT) as Boolean
        movieListViewRCV.apply {
            if(gridLayout){
                layoutManager = GridLayoutManager(activity,2)
            }
            else{
                layoutManager = LinearLayoutManager(activity)
            }
            adapter = MovieAdapter(context, data)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}