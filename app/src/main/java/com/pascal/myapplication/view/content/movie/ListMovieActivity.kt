package com.pascal.myapplication.view.content.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pascal.myapplication.R
import com.pascal.myapplication.model.ResultsItem
import com.pascal.myapplication.presenter.MoviePresenter
import com.pascal.myapplication.presenter.interFace.MovieInterface
import kotlinx.android.synthetic.main.activity_list_movie.*

class ListMovieActivity : AppCompatActivity(), MovieInterface {

    private var presenter: MoviePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movie)

        presenter = MoviePresenter(this)
        presenter?.getDataMovie()
    }

    override fun isSuccess(msg: String, data: List<ResultsItem?>?) {
       val adapter = MovieAdapter(data, object : MovieAdapter.OnClickListener {
           override fun detail(item: ResultsItem?) {
               showToast("Anda memilih ${item?.name}")
           }
       })
        recycler.adapter = adapter
    }

    override fun isError(msg: String) {
        showToast(msg)
    }

    private fun showToast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}