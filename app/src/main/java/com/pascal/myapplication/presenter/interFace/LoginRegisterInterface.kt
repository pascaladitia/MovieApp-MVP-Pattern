package com.pascal.myapplication.presenter.interFace

import com.pascal.myapplication.model.DataItem
import com.pascal.myapplication.model.ResponseData
import com.pascal.myapplication.model.ResponseLogin

interface LoginRegisterInterface {
    fun successRegister(response: ResponseData)
    fun successLogin(response: ResponseLogin, data: List<DataItem?>?)
    fun isError(msg: String)
    fun isLoading(load: Boolean)
}