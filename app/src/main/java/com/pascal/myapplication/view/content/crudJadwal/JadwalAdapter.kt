package com.pascal.myapplication.view.content.crudJadwal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pascal.myapplication.R
import com.pascal.myapplication.model.DataJadwal
import kotlinx.android.synthetic.main.item_jadwal.view.*

class JadwalAdapter(private val data: List<DataJadwal?>?,
                    private val itemClick: OnClickListener
) : RecyclerView.Adapter<JadwalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_jadwal, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.bind(item)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: DataJadwal?) {
            view.itemJadwal_nama.text = item?.jadwalNama
            view.itemJadwal_jenis.text = item?.jadwalJenis
            view.itemJadwal_tgl.text = "Tgl: ${item?.jadwalTgl}"

            view.itemJadwal_update.setOnClickListener{
                itemClick.update(item)
            }

            view.itemJadwal_delete.setOnClickListener {
                itemClick.delete(item)
            }

            view.setOnClickListener {
                itemClick.detail(item)
            }
        }
    }

    interface OnClickListener {
        fun update(item: DataJadwal?)
        fun delete(item: DataJadwal?)
        fun detail(item: DataJadwal?)
    }
}