package com.pascal.myapplication.view.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.pascal.myapplication.R
import com.pascal.myapplication.model.DataItem
import com.pascal.myapplication.model.ResponseData
import com.pascal.myapplication.model.ResponseLogin
import com.pascal.myapplication.presenter.LoginRegisterPresenter
import com.pascal.myapplication.presenter.interFace.LoginRegisterInterface
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), LoginRegisterInterface {

    private var presenter: LoginRegisterPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        presenter = LoginRegisterPresenter(this)

        registerCode()
    }

    private fun registerCode() {
        btn_register.setOnClickListener {
            val nama = edt_RegisterNama.text.toString()
            val hp = edt_RegisterHp.text.toString().trim()
            val email = edt_RegisterEmail.text.toString().trim()
            val password = edt_RegisterPassword.text.toString().trim()

            presenter?.registerData(nama, hp, email, password)
        }

        btn_cancel.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun successRegister(response: ResponseData) {
        showToast("Register Success")
        onBackPressed()
    }

    override fun successLogin(response: ResponseLogin, data: List<DataItem?>?) {

    }

    override fun isError(msg: String) {
        showToast(msg)
    }

    override fun isLoading(load: Boolean) {
        if (load == true) {
            progress_register.visibility = View.VISIBLE
        } else {
            progress_register.visibility = View.GONE
        }
    }
}