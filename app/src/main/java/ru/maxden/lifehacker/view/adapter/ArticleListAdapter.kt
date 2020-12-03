package ru.maxden.lifehacker.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maxden.lifehacker.databinding.ViewArticleListItemBinding
import ru.maxden.lifehacker.model.pojo.ArticleItem
import ru.maxden.lifehacker.view.adapter.viewHolders.ArticleListViewHolder
import ru.maxden.lifehacker.view.ui.articlelist.ArticleListViewModel

class ArticleListAdapter(private val articleListViewModel: ArticleListViewModel) : RecyclerView.Adapter<ArticleListViewHolder>() {
    var articleList: List<ArticleItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ViewArticleListItemBinding.inflate(inflater, parent, false)
        return ArticleListViewHolder(dataBinding, articleListViewModel)
    }

    override fun getItemCount() = articleList.size

    override fun onBindViewHolder(holder: ArticleListViewHolder, position: Int) {
        holder.setup(articleList[position])
    }

    fun updateArticleList(articleList: List<ArticleItem>) {
        this.articleList = articleList
        notifyDataSetChanged()
    }
}