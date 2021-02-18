package com.pascal.myapplication.presenter

import com.pascal.myapplication.network.ConfigNetwork
import com.pascal.myapplication.presenter.interFace.MovieInterface
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MoviePresenter(val movieView: MovieInterface) {

    fun getDataMovie() {
        ConfigNetwork.serviceMovie().getDataMovie().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.results != null) {
                    movieView.isSuccess("Data Kosong", it.results)
                }
            }, {
                movieView.isError(it.message ?: "")
            })
    }
}