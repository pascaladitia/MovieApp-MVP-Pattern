package com.pascal.myapplication.model

import com.google.gson.annotations.SerializedName

data class ResponseData(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)
