package com.pascal.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseJadwal(

	@field:SerializedName("data")
	val data: List<DataJadwal?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
) : Parcelable

@Parcelize
data class DataJadwal(

	@field:SerializedName("jadwal_nama")
	val jadwalNama: String? = null,

	@field:SerializedName("jadwal_id")
	val jadwalId: String? = null,

	@field:SerializedName("jadwal_tgl")
	val jadwalTgl: String? = null,

	@field:SerializedName("jadwal_jenis")
	val jadwalJenis: String? = null
) : Parcelable
