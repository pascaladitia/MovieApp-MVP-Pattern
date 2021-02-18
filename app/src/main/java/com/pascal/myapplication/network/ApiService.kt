package com.pascal.myapplication.network

import com.pascal.myapplication.model.ResponseData
import com.pascal.myapplication.model.ResponseJadwal
import com.pascal.myapplication.model.ResponseLogin
import com.pascal.myapplication.model.ResponseMovie
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    //getMovie
    @GET("3/movie/5/lists?api_key=b64d761def5c00e40e6a36e0032741bf&language=en-US&page=3&total_result=200")
    fun getDataMovie(): Flowable<ResponseMovie>

    //register
    @FormUrlEncoded
    @POST("register.php")
    fun registerData(
        @Field("user_nama") nama: String,
        @Field("user_hp") hp: String,
        @Field("user_email") email: String,
        @Field("user_password") password: String
    ): Flowable<ResponseData>

    //login
    @FormUrlEncoded
    @POST("login.php")
    fun loginData(
        @Field("user_email") email: String,
        @Field("user_password") password: String
    ): Flowable<ResponseLogin>


    //getData
    @GET("jadwal/getData.php")
    fun getDataJadwal(): Flowable<ResponseJadwal>

    //insert
    @FormUrlEncoded
    @POST("jadwal/insert.php")
    fun insertJadwal(
        @Field("jadwal_nama") nama: String,
        @Field("jadwal_tgl") tgl: String,
        @Field("jadwal_jenis") jenis: String
    ): Flowable<ResponseData>

    //update
    @FormUrlEncoded
    @POST("jadwal/update.php")
    fun updateJadwal(
        @Field("jadwal_id") id: String,
        @Field("jadwal_nama") nama: String,
        @Field("jadwal_tgl") tgl: String,
        @Field("jadwal_jenis") jenis: String
    ): Flowable<ResponseData>

    //delete
    @FormUrlEncoded
    @POST("jadwal/delete.php")
    fun deleteJadwal(
        @Field("jadwal_id") id: String
    ): Flowable<ResponseData>
}