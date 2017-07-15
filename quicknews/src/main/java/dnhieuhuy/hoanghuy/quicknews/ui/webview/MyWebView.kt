package dnhieuhuy.hoanghuy.quicknews.ui.webview

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import dnhieuhuy.hoanghuy.quicknews.R
import dnhieuhuy.hoanghuy.quicknews.model.NewsArticles

/**
 * Created by Administrator on 15/07/2017.
 */
class MyWebView: WebViewClient()
{
    fun initMyWebView(view: View, news: NewsArticles)
    {
        var progessBarWebView = view.findViewById(R.id.progess_bar_webview) as ProgressBar
        progessBarWebView.progressDrawable.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)

        var myWebView = view.findViewById(R.id.url_webview) as WebView
        myWebView.settings.loadsImagesAutomatically = true
        myWebView.settings.javaScriptEnabled = true
        myWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        myWebView.settings.builtInZoomControls = true

        myWebView.setWebViewClient(object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }

            override fun onLoadResource(view: WebView?, url: String?) {
                super.onLoadResource(view, url)
                if(view != null)
                {
                    progessBarWebView.progress = view.progress
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progessBarWebView.invalidate()
            }
        })


        myWebView.setWebChromeClient(object : WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                progessBarWebView.progress = newProgress
                if (newProgress == 100)
                {
                    progessBarWebView.invalidate() //---> Invalidate Your Progess Bar
                }
            }
        })


        //Get url from intent
        try{
            myWebView.loadUrl(news.url)
        } catch(ex: Exception)
        {
            ex.printStackTrace()
        }

    }
}