package com.pascal.myapplication.presenter

import com.pascal.myapplication.network.ConfigNetwork
import com.pascal.myapplication.presenter.interFace.LoginRegisterInterface
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginRegisterPresenter(val loginRegisterView: LoginRegisterInterface) {

    fun registerData(nama: String, hp: String, email: String, password: String) {
            loginRegisterView.isLoading(true)
        if (nama.isNotEmpty() && hp.isNotEmpty() &&
            email.isNotEmpty() && password.isNotEmpty()) {

            loginRegisterView.isLoading(false)

            if (password.length >= 6) {
                loginRegisterView.isLoading(false)
                ConfigNetwork.service().registerData(nama, hp, email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        loginRegisterView.isLoading(false)
                        if (it.isSuccess!!.equals("true")) {
                            loginRegisterView.successRegister(it)
                        } else {
                            loginRegisterView.isError(it.message.toString())
                        }
                    }, {
                        loginRegisterView.isLoading(false)
                        loginRegisterView.isError(it.localizedMessage)
                    })
            } else {
                loginRegisterView.isLoading(false)
                loginRegisterView.isError("Password harus lebih dari 6 karakter")
            }
        } else {
            loginRegisterView.isLoading(false)
            loginRegisterView.isError("Kolom tidak boleh ada yang kosong")
        }
    }

    fun loginData(email: String, password: String) {
        loginRegisterView.isLoading(true)
        if (email.isNotEmpty() && password.isNotEmpty()) {

            loginRegisterView.isLoading(false)

            if (password.length >= 6) {
                loginRegisterView.isLoading(false)
                ConfigNetwork.service().loginData(email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        loginRegisterView.isLoading(false)
                        loginRegisterView.successLogin(it, it.data)
                    }, {
                        loginRegisterView.isLoading(false)
                        loginRegisterView.isError("Error")
                    })
            } else {
                loginRegisterView.isLoading(false)
                loginRegisterView.isError("Password harus lebih dari 6 karakter")
            }
        } else {
            loginRegisterView.isLoading(false)
            loginRegisterView.isError("Kolom tidak boleh ada yang kosong")
        }
    }
}