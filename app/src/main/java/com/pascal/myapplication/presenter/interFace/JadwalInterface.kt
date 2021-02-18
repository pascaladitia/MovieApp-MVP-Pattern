package com.pascal.myapplication.presenter.interFace

import com.pascal.myapplication.model.DataJadwal
import com.pascal.myapplication.model.ResponseData
import com.pascal.myapplication.model.ResponseJadwal

interface JadwalInterface {
    fun getSuccess(response: ResponseJadwal, data: List<DataJadwal?>?)
    fun dataSuccess(response: ResponseData)
    fun isError(msg: String)
    fun isLoading(load: Boolean)
}