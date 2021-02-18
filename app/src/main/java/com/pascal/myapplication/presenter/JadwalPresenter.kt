package com.pascal.myapplication.presenter

import com.pascal.myapplication.network.ConfigNetwork
import com.pascal.myapplication.presenter.interFace.JadwalInterface
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class JadwalPresenter(val jadwalView: JadwalInterface) {

    fun getJadwal() {
        jadwalView.isLoading(true)
        ConfigNetwork.service().getDataJadwal()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                jadwalView.isLoading(false)
                jadwalView.getSuccess(it, it.data)
            }, {
                jadwalView.isLoading(false)
                jadwalView.isError(it.localizedMessage)
            })
    }

    fun insertJadwal(nama: String, tgl: String, jenis: String) {
        jadwalView.isLoading(true)

        if (nama.isNotEmpty() && tgl.isNotEmpty() && jenis.isNotEmpty()) {
            jadwalView.isLoading(false)
            ConfigNetwork.service().insertJadwal(nama, tgl, jenis)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    jadwalView.isLoading(false)
                    jadwalView.dataSuccess(it)
                }, {
                    jadwalView.isLoading(false)
                    jadwalView.isError(it.localizedMessage)
                })
        } else {
            jadwalView.isLoading(false)
            jadwalView.isError("Kolom tidak boleh ada yang kosong")
        }
    }

    fun updateJadwal(id: String, nama: String, tgl: String, jenis: String) {
        jadwalView.isLoading(true)

        if (nama.isNotEmpty() && tgl.isNotEmpty() && jenis.isNotEmpty()) {
            jadwalView.isLoading(false)
            ConfigNetwork.service().updateJadwal(id, nama, tgl, jenis)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    jadwalView.isLoading(false)
                    jadwalView.dataSuccess(it)
                }, {
                    jadwalView.isLoading(false)
                    jadwalView.isError(it.localizedMessage)
                })
        } else {
            jadwalView.isLoading(false)
            jadwalView.isError("Kolom tidak boleh ada yang kosong")
        }
    }

    fun deleteJadwal(id: String) {
        jadwalView.isLoading(true)
        ConfigNetwork.service().deleteJadwal(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                jadwalView.isLoading(false)
                jadwalView.dataSuccess(it)
            }, {
                jadwalView.isLoading(false)
                jadwalView.isError(it.localizedMessage)
            })
    }
}