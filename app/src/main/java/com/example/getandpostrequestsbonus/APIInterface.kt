package com.example.getandpostrequestsbonus

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {

     // @Headers("Content-Type: application/json")
    @GET("/custom-people/")
    fun getUserDetails(): Call<List<UsersDetails.Datum>>

    // @Headers("Content-Type: application/json")
    @POST("/custom-people/")
    fun addUserDetails(@Body userdata:UsersDetails.Datum): Call<UsersDetails.Datum>

}