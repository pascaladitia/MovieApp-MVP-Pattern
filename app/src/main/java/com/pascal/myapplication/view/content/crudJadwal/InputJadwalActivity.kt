package com.pascal.myapplication.view.content.crudJadwal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.pascal.myapplication.R
import com.pascal.myapplication.model.DataJadwal
import com.pascal.myapplication.model.ResponseData
import com.pascal.myapplication.model.ResponseJadwal
import com.pascal.myapplication.presenter.JadwalPresenter
import com.pascal.myapplication.presenter.interFace.JadwalInterface
import kotlinx.android.synthetic.main.activity_input_jadwal.*

class InputJadwalActivity : AppCompatActivity(), JadwalInterface {

    private var presenter: JadwalPresenter? = null
    private var item: DataJadwal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_jadwal)
        supportActionBar?.title = "Input Activity"

        presenter = JadwalPresenter(this)

        initView()
    }

    private fun initView() {
        item = intent.getParcelableExtra("data")
        if (item != null) {
            jadwal_nama.setText(item?.jadwalNama)
            jadwal_tanggal.setText(item?.jadwalTgl)
            jadwal_jenis.setText(item?.jadwalJenis)

            btnJadwal_insert.text = "Update"
        }

        when (btnJadwal_insert.text) {
            "Update" -> {
                btnJadwal_insert.setOnClickListener {
                        presenter?.updateJadwal(
                            item?.jadwalId.toString(),
                            jadwal_nama.text.toString(),
                            jadwal_tanggal.text.toString(),
                            jadwal_jenis.text.toString()
                        )
                }
            }
            else -> {
                btnJadwal_insert.setOnClickListener {
                    presenter?.insertJadwal(
                        jadwal_nama.text.toString(),
                        jadwal_tanggal.text.toString(),
                        jadwal_jenis.text.toString()
                    )
                }
            }
        }
    }

    override fun getSuccess(response: ResponseJadwal, data: List<DataJadwal?>?) {
        TODO("Not yet implemented")
    }

    override fun dataSuccess(response: ResponseData) {
        showToast("Success Insert Data")
        finish()
    }

    override fun isError(msg: String) {
        showToast(msg)
    }

    override fun isLoading(load: Boolean) {
        if (load == true) {
            progress_input.visibility = View.VISIBLE
        } else {
            progress_input.visibility = View.GONE
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}