package dnhieuhuy.hoanghuy.quicknews.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Administrator on 14/07/2017.
 */
class NewsArticles(
        @SerializedName("author") var author: String = "",
        @SerializedName("title") var title: String = "",
        @SerializedName("description") var description: String = "",
        @SerializedName("url") var url: String = "",
        @SerializedName("urlToImage") var urlToImage: String = "",
        @SerializedName("publishedAt") var publishedAt: String = ""
){}