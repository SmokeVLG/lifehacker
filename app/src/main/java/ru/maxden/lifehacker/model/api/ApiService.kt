package ru.maxden.lifehacker.model.api

import ru.maxden.lifehacker.model.pojo.ListArticle
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    fun getListArticle(): Call<ListArticle>
}