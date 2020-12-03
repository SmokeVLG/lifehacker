package ru.maxden.lifehacker.model

import ru.maxden.lifehacker.model.api.ApiClient
import ru.maxden.lifehacker.model.pojo.ListArticle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    fun getArticleList(onResult: (isSuccess: Boolean, response: ListArticle?) -> Unit) {

        ApiClient.instance.getListArticle().enqueue(object : Callback<ListArticle> {
            override fun onResponse(call: Call<ListArticle>?, response: Response<ListArticle>?) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<ListArticle>?, t: Throwable?) {
                onResult(false, null)
            }

        })
    }

    companion object {
        private var INSTANCE: Repository? = null
        fun getInstance() = INSTANCE
                ?: Repository().also {
                    INSTANCE = it
                }
    }
}