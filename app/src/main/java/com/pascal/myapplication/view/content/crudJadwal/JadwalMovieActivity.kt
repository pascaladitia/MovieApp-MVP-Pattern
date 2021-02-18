package com.pascal.myapplication.view.content.crudJadwal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.pascal.myapplication.R
import com.pascal.myapplication.model.DataJadwal
import com.pascal.myapplication.model.ResponseData
import com.pascal.myapplication.model.ResponseJadwal
import com.pascal.myapplication.presenter.JadwalPresenter
import com.pascal.myapplication.presenter.interFace.JadwalInterface
import kotlinx.android.synthetic.main.activity_jadwal_movie.*

class JadwalMovieActivity : AppCompatActivity(), JadwalInterface {

    private var presenter: JadwalPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal_movie)

        presenter = JadwalPresenter(this)
        presenter?.getJadwal()

        initView()
    }

    private fun initView() {
        btn_jadwalAdd.setOnClickListener {
            val intent = Intent(this@JadwalMovieActivity, InputJadwalActivity::class.java)
            startActivity(intent)
        }
    }

    override fun getSuccess(response: ResponseJadwal, data: List<DataJadwal?>?) {
        val adapter = JadwalAdapter(data, object : JadwalAdapter.OnClickListener {
            override fun update(item: DataJadwal?) {
                val intent = Intent(this@JadwalMovieActivity, InputJadwalActivity::class.java)
                intent.putExtra("data", item)
                startActivity(intent)
            }

            override fun delete(item: DataJadwal?) {
                AlertDialog.Builder(this@JadwalMovieActivity).apply {
                    setTitle("Delete Data")
                    setMessage("Are you sure?")
                    setPositiveButton("Yes") {dialogInterface, i ->
                        presenter?.deleteJadwal(item?.jadwalId!!)
                        presenter?.getJadwal()
                        dialogInterface.dismiss()
                    }
                    setNegativeButton("No") {dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                }.show()
            }

            override fun detail(item: DataJadwal?) {
                showToast("Jadwal ${item?.jadwalNama} pada tanggal ${item?.jadwalTgl}")
            }

        })
        recycler_jadwal.adapter = adapter
    }

    override fun dataSuccess(response: ResponseData) {
        TODO("Not yet implemented")
    }

    override fun isError(msg: String) {
        showToast(msg)
    }

    override fun isLoading(load: Boolean) {
        if (load == true) {
            progress_jadwal.visibility = View.VISIBLE
        } else {
            progress_jadwal.visibility = View.GONE
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        presenter?.getJadwal()
    }
}