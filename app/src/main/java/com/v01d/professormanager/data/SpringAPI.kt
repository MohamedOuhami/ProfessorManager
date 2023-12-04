package com.v01d.professormanager.data

import com.v01d.professormanager.entities.Professor
import com.v01d.professormanager.entities.Specialite
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SpringAPI {

    companion object {
        const val BASE_URL = "http://192.168.1.158:8080"
    }

//    Specialites

    @GET("/api/v1/specialites")
    fun getSpecialites():Call<List<Specialite>>

    @GET("/api/v1/specialites/{id}")
    fun getSpecialiteById(@Path("id") id:Int):Call<Specialite>

    @POST("/api/v1/specialites")
    fun createSpecialite(@Body specialite:Specialite):Call<Void>

    @PUT("/api/v1/specialites/{id}")
    fun updateSpecialite(@Path("id") id:Int,@Body newSpecialite: Specialite):Call<Void>

    @DELETE("/api/v1/specialites/{id}")
    fun deleteSpecialite(@Path("id") id: Int):Call<Void>

//    Professors

    @GET("/api/v1/professors")
    fun getProfessor():Call<List<Professor>>

    @GET("/api/v1/professors/{id}")
    fun getProfessorById(@Path("id") id:Int):Call<Professor>

    @GET("/api/v1/professors/bySpecialite/{specialiteId}")
    fun getProfessorBySpecialite(@Path("specialiteId") id:Int):Call<List<Professor>>

    @POST("/api/v1/professors")
    fun createProfessor(@Body professor: Professor):Call<Void>

    @PUT("/api/v1/professors/{id}")
    fun updateProfessor(@Path("id") id:Int,@Body newProfessor: Professor):Call<Void>

    @DELETE("/api/v1/professors/{id}")
    fun deleteProfessor(@Path("id") id: Int):Call<Void>

}