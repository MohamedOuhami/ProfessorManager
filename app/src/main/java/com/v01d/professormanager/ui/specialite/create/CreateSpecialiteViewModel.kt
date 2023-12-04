package com.v01d.professormanager.ui.specialite.create

import android.text.Editable
import android.util.Log
import androidx.lifecycle.ViewModel
import com.v01d.professormanager.data.RetrofitClient
import com.v01d.professormanager.entities.Specialite
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateSpecialiteViewModel : ViewModel() {

    fun createSpecialite(name: String){
        val specialite:Specialite = Specialite(0,name)

        val call: Call<Void> = RetrofitClient.getInstance().springAPI.createSpecialite(specialite)

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