package com.v01d.professormanager.ui.professor.show

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.v01d.professormanager.data.RetrofitClient
import com.v01d.professormanager.entities.Professor
import com.v01d.professormanager.entities.Specialite
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowProfessorViewModel : ViewModel() {

    val professorList: MutableLiveData<List<Professor>> by lazy {
        MutableLiveData<List<Professor>>().also {
            // Initialize with an empty list
            it.value = emptyList()
        }
    }

    init {
        getProfessors()
    }

    fun getProfessors() {
//        Create a call to get the specialite
        val call: Call<List<Professor>> = RetrofitClient.getInstance().springAPI.getProfessor()

//        Queue the call to add the logic to what's gonna be done with the data
        call.enqueue(object : Callback<List<Professor>> {
            override fun onResponse(
                call: Call<List<Professor>>,
                response: Response<List<Professor>>
            ) {
                professorList.value = response.body() ?: emptyList()
                Log.d(
                    "Call Success",
                    "The call was successful from ${response.raw().request().url()}}"
                )
            }

            override fun onFailure(call: Call<List<Professor>>, t: Throwable) {
                Log.d("Error with the call", "The call failed because of ${t.message}")
            }
        })


        }
    fun deleteProfessor(id: Int) {
        val call: Call<Void> = RetrofitClient.getInstance().springAPI.deleteProfessor(id)

//        Queue the call to add the logic to what's gonna be done with the data
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.d(
                    "Call Success",
                    "The call was successful from ${response.raw().request().url()}}"
                )
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("Error with the call", "The call failed because of ${t.message}")
            }
        })

        getProfessors()
    }}
