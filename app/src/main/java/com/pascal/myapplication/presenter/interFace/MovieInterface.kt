package com.pascal.myapplication.presenter.interFace

import com.pascal.myapplication.model.ResultsItem

interface MovieInterface {
    fun isSuccess(msg: String, data: List<ResultsItem?>?)
    fun isError(msg: String)
}