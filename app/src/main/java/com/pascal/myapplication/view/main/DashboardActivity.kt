package com.pascal.myapplication.view.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.pascal.myapplication.R
import com.pascal.myapplication.utils.SessionManager
import com.pascal.myapplication.view.content.*
import com.pascal.myapplication.view.content.crudJadwal.JadwalMovieActivity
import com.pascal.myapplication.view.content.movie.ListMovieActivity
import com.pascal.myapplication.view.login.LoginActivity
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    companion object {
        const val NAME = "LOGIN"
        const val LOGIN_SESSION = "login_session"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        supportActionBar?.hide()

        go_profil.setOnClickListener {
            startActivity(Intent(this, ProfilActivity::class.java))
        }
        go_movie.setOnClickListener {
            startActivity(Intent(this, ListMovieActivity::class.java))
        }
        go_location.setOnClickListener {
            startActivity(Intent(this, LokasiSayaActivity::class.java))
        }
        go_aktor.setOnClickListener {
            startActivity(Intent(this, AktorMovieActivity::class.java))
        }
        go_jadwal.setOnClickListener {
            startActivity(Intent(this, JadwalMovieActivity::class.java))
        }
        go_studio.setOnClickListener {
            startActivity(Intent(this, StudioMovieActivity::class.java))
        }

        btn_logout.setOnClickListener {
            AlertDialog.Builder(this@DashboardActivity).apply {
                setTitle("Logout")
                setMessage("Are you sure?")
                setPositiveButton("yes") { dialogInterface, i ->

                    var intent = Intent(this@DashboardActivity, LoginActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)

                    getSharedPreferences(NAME, Context.MODE_PRIVATE).edit()
                        .putInt(LOGIN_SESSION, 0).commit()
                    dialogInterface.dismiss()
                }
                setNegativeButton(R.string.cancel) { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
            }.show()
        }
    }
}