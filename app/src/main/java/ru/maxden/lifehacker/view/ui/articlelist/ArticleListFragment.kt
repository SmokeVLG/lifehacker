package ru.maxden.lifehacker.view.ui.articlelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxden.lifehacker.databinding.FragmentArticleListBinding
import ru.maxden.lifehacker.view.adapter.ArticleListAdapter
import kotlinx.android.synthetic.main.fragment_article_list.*
import org.jetbrains.anko.longToast

class ArticleListFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentArticleListBinding
    private lateinit var adapter: ArticleListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentArticleListBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@ArticleListFragment).get(ArticleListViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel?.fetchArticleList()

        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.articleListLive?.observe(viewLifecycleOwner, Observer {
            adapter.updateArticleList(it)
        })

        viewDataBinding.viewmodel?.toastMessage?.observe(viewLifecycleOwner, Observer {
            activity?.longToast(it)
        })
    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = ArticleListAdapter(viewDataBinding.viewmodel!!)
            val layoutManager = LinearLayoutManager(activity)
            article_list_rv.layoutManager = layoutManager
            article_list_rv.addItemDecoration(DividerItemDecoration(activity, layoutManager.orientation))
            article_list_rv.adapter = adapter
        }
    }
}
