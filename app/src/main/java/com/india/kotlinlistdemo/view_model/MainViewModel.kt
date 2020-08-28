package com.india.kotlinlistdemo.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.india.kotlinlistdemo.api.ApiClient
import com.india.kotlinlistdemo.api.ApiInterface
import com.india.kotlinlistdemo.model.Data
import com.india.kotlinlistdemo.model.StudiosResponse
import retrofit2.Callback

class MainViewModel(application: Application): AndroidViewModel(application) {
    val studiosResponse = MutableLiveData<StudiosResponse>()
    val studioError = MutableLiveData<String>()
    val studioList = MutableLiveData<List<Data>>()


    fun getStudiosApi(pageNum:String, lat: String, long:String){
        val api: retrofit2.Call<StudiosResponse> = ApiClient.client.create(ApiInterface::class.java).getStudios(pageNum, lat, long )
        api.enqueue(object : Callback<StudiosResponse> {
            override fun onFailure(call: retrofit2.Call<StudiosResponse>, t: Throwable) {


            }

            override fun onResponse(call: retrofit2.Call<StudiosResponse>, response: retrofit2.Response<StudiosResponse>) {
                val res: StudiosResponse? = response.body()
                if (res!=null){
                    studiosResponse.value = res
                }

                }
        })
    }
}