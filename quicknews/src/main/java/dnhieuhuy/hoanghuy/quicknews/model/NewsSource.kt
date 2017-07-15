package dnhieuhuy.hoanghuy.quicknews.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Administrator on 14/07/2017.
 */
class NewsSource(@SerializedName("status") var status: String,
                 @SerializedName("source") var source: String,
                 @SerializedName("sortBy") var sortBy: String,
                 @SerializedName("articles") var articles: List<NewsArticles>) {
}