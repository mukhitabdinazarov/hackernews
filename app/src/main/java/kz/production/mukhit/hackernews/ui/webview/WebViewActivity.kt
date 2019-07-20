package kz.production.mukhit.hackernews.ui.webview

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*
import kz.production.mukhit.hackernews.R

class WebViewActivity : AppCompatActivity() {

    private var BASE_URL : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        if(intent.hasExtra("base_url")){
            BASE_URL = intent.getStringExtra("base_url")
        }
        else{
            BASE_URL = ""
        }
        connect()

        repeatBtn.setOnClickListener {
            connect()
        }
    }

    fun connect(){
        if(isNetworkAvailable()){
            hideErrorLayout()
            webView.webViewClient = WebViewClient()
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(BASE_URL)
            webView.setWebViewClient(object : WebViewClient(){
                override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                    showErrorLayout()
                }
            })
        }
        else{
            showErrorLayout()
        }
    }

    fun isNetworkAvailable(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        val networkInfo = cm!!.activeNetworkInfo

        return if (networkInfo != null && networkInfo.isConnected) {
            true
        } else false
    }

    fun showErrorLayout(){
        webView.visibility = View.GONE
        webViewErrorLayout.visibility = View.VISIBLE
    }

    fun hideErrorLayout(){
        webViewErrorLayout.visibility = View.GONE
        webView.visibility = View.VISIBLE
    }
}
