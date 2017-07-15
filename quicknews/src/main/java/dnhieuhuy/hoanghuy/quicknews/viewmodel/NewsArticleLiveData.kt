package dnhieuhuy.hoanghuy.quicknews.viewmodel

import android.arch.lifecycle.LiveData
import dnhieuhuy.hoanghuy.quicknews.api.NewsArticleService
import dnhieuhuy.hoanghuy.quicknews.api.RestApi
import dnhieuhuy.hoanghuy.quicknews.model.NewsArticles
import dnhieuhuy.hoanghuy.quicknews.model.NewsSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * The data holder component which observes for data changes and notifies
 * the observers about the data change.
 **/

class  NewsArticleLiveData(articleSource: String): LiveData<List<NewsArticles>>()
{
    /**
     * Init Block to fetch the news and notify the observers about
     * the data change
     */

    init {
        val newsArticleService =RestApi.retrofit.create(NewsArticleService::class.java)
        val newArticles = newsArticleService.getAllNewsArticles(articleSource)
        newArticles.enqueue(object : Callback<NewsSource> {
            override fun onFailure(call: Call<NewsSource>?, t: Throwable?) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<NewsSource>?, response: Response<NewsSource>?) {
                if (response != null) {
                    // Notify observers about data change
                    value = response.body().articles
                }
            }

        })
    }
}