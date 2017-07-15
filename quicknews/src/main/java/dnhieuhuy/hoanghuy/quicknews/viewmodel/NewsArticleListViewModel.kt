package com.akshay.newsapp.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import dnhieuhuy.hoanghuy.quicknews.model.NewsArticles
import dnhieuhuy.hoanghuy.quicknews.viewmodel.NewsArticleLiveData


/**
 * The component to separate the data and UI part.
 * It even survives configuration changes.
 *
 * @author Akshay Chordiya
 * @since 5/23/2017.
 */
class NewsArticleListViewModel( application: Application?) : AndroidViewModel(application) {
    /**
     * The data held for UI
     */
    private var newsArticlesList: NewsArticleLiveData? = null

    /**
     * Get live data reference of all the news articles to observe for changes
     */
    fun getNewsArticles(articlesSource : String ): LiveData<List<NewsArticles>> {
        newsArticlesList = NewsArticleLiveData(articlesSource)
        return newsArticlesList as NewsArticleLiveData
    }
}