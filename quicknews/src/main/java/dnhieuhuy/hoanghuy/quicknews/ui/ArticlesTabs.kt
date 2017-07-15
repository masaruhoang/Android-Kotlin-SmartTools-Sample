package com.example.administrator.myapplication.tabLayout

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.akshay.newsapp.ui.NewsArticleListViewModel
import dnhieuhuy.hoanghuy.quicknews.R
import dnhieuhuy.hoanghuy.quicknews.adapter.NewsArticlesAdapter
import dnhieuhuy.hoanghuy.quicknews.model.NewsArticles

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [fragment_page.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [fragment_page.newInstance] factory method to
 * create an instance of this fragment.
 * Created by Administrator on 18/06/2017.
 */
class ArticlesTabs: LifecycleFragment(){

    /**
     * Declaring variables is in HERE
     * **/
    private var mPage: String = ""

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPage = arguments.getString(ARG_PAGE)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.articles_tabs, container, false)

        //TextView
        val textV: TextView = view.findViewById(R.id.tvTitle) as TextView
        textV.text =  mPage.toUpperCase()

        //Setting RecycleView
        try {
            var news_list: RecyclerView = view?.findViewById(R.id.news_list) as RecyclerView
            val newsArticleListViewModel = ViewModelProviders.of(this).get(NewsArticleListViewModel::class.java)
            //Setting up RecycleView And adapter
            val adapter = NewsArticlesAdapter{}
            news_list.adapter = adapter
            news_list.layoutManager = LinearLayoutManager(this.context)

            //Observing for data change
            newsArticleListViewModel.getNewsArticles(mPage).observe(this, Observer<List<NewsArticles>> {
                article ->
                if (article != null )
                {
                    //Update the UI as the data has changed
                    adapter.replaceItems(article)
                    adapter.notifyDataSetChanged()
                }
            })
        }catch (ex: Exception)
        {
            ex.printStackTrace()
        }

        return view
    }

    //Conpanion Object
    companion object{
        val ARG_PAGE = "ARG_PAGE"

        fun newInstance(page: String): ArticlesTabs
        {
            val args = Bundle()
            args.putString(ARG_PAGE, page)
            val fragment = ArticlesTabs()
            fragment.arguments = args
            return fragment
        }
    }
}