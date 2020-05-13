package com.example.exo4_advenced

import retrofit2.Call
import retrofit2.http.GET


interface TodosService{
    @GET("todos")
    fun getPost(): Call<ArrayList<Todo>>
}