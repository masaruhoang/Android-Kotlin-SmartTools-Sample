package dnhieuhuy.hoanghuy.quicknews.api

import dnhieuhuy.hoanghuy.quicknews.model.NewsSource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Administrator on 14/07/2017.
 */
interface NewsArticleService {
    /**
     * Retrivies all the latest news aricle from Google News
     * */
    @GET("articles?" + "&sortBy=top&apiKey=41f3468cef834398bea8c11cc724db69")
    fun getAllNewsArticles(@Query("source") ns: String?): Call<NewsSource>
}