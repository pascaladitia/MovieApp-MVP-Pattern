package com.pascal.myapplication.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.pascal.myapplication.R
import com.pascal.myapplication.utils.SessionManager
import com.pascal.myapplication.view.login.LoginActivity

class MainActivity : AppCompatActivity() {

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val session = SessionManager(this)

        handler = Handler()
        handler.postDelayed(Runnable{

            if (session.login == true) {
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, 2000)
    }
}