package dnhieuhuy.hoanghuy.quicknews.adapter

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import dnhieuhuy.hoanghuy.quicknews.R
import dnhieuhuy.hoanghuy.quicknews.model.NewsArticles
import dnhieuhuy.hoanghuy.quicknews.ui.webview.MyWebView
import dnhieuhuy.hoanghuy.quicknews.utils.inflate
import kotlinx.android.synthetic.main.news_articles_item.view.*

/**
 * Created by Administrator on 15/07/2017.
 */
class NewsArticlesAdapter(val listener: (NewsArticles) -> Unit) : RecyclerView.Adapter<NewsArticlesAdapter.NewsHolders>()
{
    /**
     * List of News Articles
     * **/
    private var newsArticles: List<NewsArticles> = emptyList()

    /**
     * Bind the view with the data
     */
    override fun onBindViewHolder(newsHolder: NewsHolders?, position: Int)  = newsHolder!!.bind(newsArticles[position], listener)
    /**
     * Inflate the view
     * **/
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = NewsHolders(parent!!.inflate(R.layout.news_articles_item))

    override fun getItemCount() = newsArticles.size

    /**
     * View Holder Pattern
     */
    class NewsHolders(itemView: View): RecyclerView.ViewHolder(itemView) {
        /**
         * Binds the UI with the data and handles clicks
         */
        fun bind(newsArticle: NewsArticles, listener: (NewsArticles) -> Unit) = with(itemView)
        {
            Picasso.with(context).load(newsArticle.urlToImage).into(news_image)
            news_title.text = newsArticle.title
            news_description.text = newsArticle.description

            news_author.text = if(newsArticle.author != null) newsArticle.author else "NEWSBOOK"
            news_pubishedAt.text = if(newsArticle.publishedAt != null) newsArticle.publishedAt else "2017"

            setOnClickListener {
                dialogURLWebView(this.context, newsArticle)
            }
        }

        /***
         * Swap function to set new data on updating
         * */
        private fun  dialogURLWebView(context: Context?, newsArticle: NewsArticles)
        {
            //Call DialogAlert displaying detailed News Paper by WebSite
            val webByDialog: AlertDialog.Builder = AlertDialog.Builder(context)
            val dialogInstance: AlertDialog = webByDialog.create()
            val factory: LayoutInflater = LayoutInflater.from(context)
            val view: View = factory.inflate(R.layout.url_webdialog_screen, null)


            //init MyWebView func----tions
            val myWebView = MyWebView()
            myWebView.initMyWebView(view, newsArticle)

            dialogInstance?.setCanceledOnTouchOutside(true)
            webByDialog?.setView(view)
            webByDialog?.show()
        }

    }


    /**
     * Swap function to set new data on updating
     */
    fun replaceItems(items: List<NewsArticles>) {
        newsArticles = items
    }

}