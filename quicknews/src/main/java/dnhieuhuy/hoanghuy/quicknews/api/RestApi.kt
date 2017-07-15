package dnhieuhuy.hoanghuy.quicknews.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Administrator on 14/07/2017.
 */
class RestApi
{
    /**
     * Kinda like a static block in Java
     * */
    companion object {
        val BASE_URL = "https://newsapi.org/v1/"
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}