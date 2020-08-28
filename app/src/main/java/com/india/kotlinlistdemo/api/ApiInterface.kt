package com.india.kotlinlistdemo.api

import com.india.kotlinlistdemo.model.StudiosResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {


    @Headers(
        "Content-Type: application/json",
        "x-appkey: rcmroes1UWF2GIcBBQ5jghe6xpwoQ4vqDqoIIcBTbZEE6",
        "x-authkey: dgfJlO10QAoZzaiT8FXrF8bgBBQ5jghe7FNrd9t8D0u",
        "http-x-device-type: sfgsdhfsdhsd",
        "x-user-id: 64710",
        "x-device_token: kjjsfhsdkjfhdsfsadkfshdafsadfhsa",
        "x-device-type:web",
        "x-app-version:2.3",
        "x-device-name:Moto G4",
        "x-device-os:7.0.1")
    @GET("studios")
    fun getStudios(@Query("page_number") pageNumber:String,
                   @Query("latitude") latitude:String,
                   @Query("page_nulongitudember") longitude:String ):Call<StudiosResponse>
}