package ru.maxden.lifehacker.view.adapter.viewHolders

import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ankit.trendinggit.BR
import com.ankit.trendinggit.R
import ru.maxden.lifehacker.model.pojo.ArticleItem
import ru.maxden.lifehacker.view.ui.articlelist.ArticleListViewModel
import ru.maxden.lifehacker.view.utils.Constants
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.view_article_list_item.view.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.sdk27.coroutines.onClick

class ArticleListViewHolder constructor(private val dataBinding: ViewDataBinding, private val articleListViewModel: ArticleListViewModel)
    : RecyclerView.ViewHolder(dataBinding.root) {

    val avatarImage = itemView.item_avatar
    fun setup(article: ArticleItem) {
        dataBinding.setVariable(BR.article, article)
        dataBinding.executePendingBindings()

        Picasso.get().load(Constants.PICTURE_URL).transform(CropCircleTransformation()).into(avatarImage);

        itemView.onClick {
            val bundle = bundleOf("url" to article.link)
            itemView.findNavController().navigate(R.id.action_articleListFragment_to_articleDetailFragment, bundle)
        }
    }
}