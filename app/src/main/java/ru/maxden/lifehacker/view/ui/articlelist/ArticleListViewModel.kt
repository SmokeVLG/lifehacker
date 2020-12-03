package ru.maxden.lifehacker.view.ui.articlelist

import androidx.lifecycle.MutableLiveData
import ru.maxden.lifehacker.model.pojo.ArticleItem
import ru.maxden.lifehacker.model.Repository
import ru.maxden.lifehacker.view.base.BaseViewModel

class ArticleListViewModel : BaseViewModel() {
    val articleListLive = MutableLiveData<List<ArticleItem>>()

    fun fetchArticleList() {
        dataLoading.value = true
        Repository.getInstance().getArticleList { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                articleListLive.value = response?.toList()
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }
}