package example.com.newsdemo.ui.newsdetail

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import example.com.newsdemo.R
import example.com.newsdemo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_news_detail.*
import ren.yale.android.cachewebviewlib.WebViewCacheInterceptorInst


class NewsDetailActivity : BaseActivity() {

    companion object {
        const val EXTRA_URL = "EXTRA_URL"

        fun createIntent(context: Context, url: String): Intent {
            return Intent(context, NewsDetailActivity::class.java).apply {
                putExtra(EXTRA_URL, url)
            }
        }
    }

    private var detailUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        intent.apply {
            detailUrl = getStringExtra(EXTRA_URL)
        }

        detailUrl?.apply {
            setupWebView()
            webViewDetail.loadUrl(this)
        }
    }

    private fun setupWebView() {
        webViewDetail.apply {
            webViewClient = object : WebViewClient() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                    WebViewCacheInterceptorInst.getInstance().loadUrl(this@apply, request.url.toString())
                    return true
                }

                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    WebViewCacheInterceptorInst.getInstance().loadUrl(this@apply, url)
                    return true
                }

                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? {
                    return WebViewCacheInterceptorInst.getInstance().interceptRequest(request)
                }

                override fun shouldInterceptRequest(view: WebView, url: String): WebResourceResponse? {
                    return WebViewCacheInterceptorInst.getInstance().interceptRequest(url)
                }
            }
        }
    }
}
