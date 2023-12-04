package com.v01d.professormanager.ui.specialite.show

import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.v01d.professormanager.data.RetrofitClient
import com.v01d.professormanager.entities.Professor
import com.v01d.professormanager.entities.Specialite
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ShowSpecialiteViewModel : ViewModel() {

    val specialitesList: MutableLiveData<List<Specialite>> by lazy {
        MutableLiveData<List<Specialite>>().also {
            // Initialize with an empty list
            it.value = emptyList()
        }
    }

    init {
        getSpecialites()
    }
    //    Fetching Data from the Spring API
    fun getSpecialites() {
//        Create a call to get the specialite
        val call:Call<List<Specialite>> = RetrofitClient.getInstance().springAPI.getSpecialites()

//        Queue the call to add the logic to what's gonna be done with the data
        call.enqueue(object : Callback<List<Specialite>> {
            override fun onResponse(call: Call<List<Specialite>>, response: Response<List<Specialite>>) {
                specialitesList.value = response.body() ?: emptyList()
                Log.d("Call Success","The call was successful from ${response.raw().request().url()}}")
            }

            override fun onFailure(call: Call<List<Specialite>>, t: Throwable) {
                Log.d("Error with the call","The call failed because of ${t.message}")
            }
        })
    }

    fun deleteSpecialite(id:Int){
        val call:Call<Void> = RetrofitClient.getInstance().springAPI.deleteSpecialite(id)

//        Queue the call to add the logic to what's gonna be done with the data
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.d("Call Success","The call was successful from ${response.raw().request().url()}}")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("Error with the call","The call failed because of ${t.message}")
            }
        })
    }


}