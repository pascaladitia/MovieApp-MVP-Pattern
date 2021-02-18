package com.pascal.myapplication.view.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.pascal.myapplication.view.main.DashboardActivity
import com.pascal.myapplication.R
import com.pascal.myapplication.model.DataItem
import com.pascal.myapplication.model.ResponseData
import com.pascal.myapplication.model.ResponseLogin
import com.pascal.myapplication.presenter.LoginRegisterPresenter
import com.pascal.myapplication.presenter.interFace.LoginRegisterInterface
import com.pascal.myapplication.utils.SessionManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginRegisterInterface {

    private var presenter: LoginRegisterPresenter? = null
    private lateinit var sharePref: SharedPreferences

    companion object {
        const val NAME = "LOGIN"
        const val LOGIN_SESSION = "login_session"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        sharePref = getSharedPreferences(NAME, Context.MODE_PRIVATE)
        presenter = LoginRegisterPresenter(this)

        loginCode()
    }

    private fun loginCode() {

        btn_login.setOnClickListener {
            val email = edt_loginEmail.text.toString()
            val password = edt_loginPassword.text.toString()

            presenter?.loginData(email, password)
        }

        btn_signUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        if (sharePref.getInt(LOGIN_SESSION, 0) == 1) {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun successRegister(response: ResponseData) {
        TODO("Not yet implemented")
    }

    override fun successLogin(response: ResponseLogin, data: List<DataItem?>?) {
        showToast("Success Login")
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()

        sharePref.edit().putInt(LOGIN_SESSION, 1).commit()
    }

    override fun isError(msg: String) {
        showToast(msg)
    }

    override fun isLoading(load: Boolean) {
        if (load == true) {
            progress_login.visibility = View.VISIBLE
        } else {
            progress_login.visibility = View.GONE
        }
    }
}