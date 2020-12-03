package ru.maxden.lifehacker.view.ui.articledetail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.maxden.lifehacker.R
import kotlinx.android.synthetic.main.fragment_article_detail.*
import org.jetbrains.anko.sdk27.coroutines.onClick


class ArticleDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_article_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val content = arguments?.let { ArticleDetailFragmentArgs.fromBundle(it).content }

        setupWebView()
        setClickListeners()

        article_web_view.loadData(content!!, "text/html", "UTF-8");
    }

    private fun setClickListeners() {
        article_back_button.onClick {
            article_web_view.goBack()
        }

        article_forward_button.onClick {
            article_web_view.goForward()
        }

        article_refresh_button.onClick {
            article_web_view.reload()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        article_web_view.setInitialScale(1)
        val webSettings = article_web_view.settings
        webSettings.setAppCacheEnabled(false)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webSettings.javaScriptEnabled = true
        webSettings.useWideViewPort = true
        webSettings.domStorageEnabled = true

        article_web_view.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                if (article_back_button != null && article_forward_button != null && article_web_view != null && article_progress_view != null) {
                    article_back_button.isEnabled = article_web_view.canGoBack()
                    article_forward_button.isEnabled = article_web_view.canGoForward()
                    article_progress_view.visibility = View.VISIBLE
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (article_back_button != null && article_forward_button != null && article_web_view != null && article_progress_view != null) {
                    article_back_button.isEnabled = article_web_view.canGoBack()
                    article_forward_button.isEnabled = article_web_view.canGoForward()
                    article_progress_view.visibility = View.GONE
                }
            }
        }
    }
}