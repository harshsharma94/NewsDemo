package example.com.newsdemo.data

import example.com.newsdemo.models.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("/v2/top-headlines")
    fun getHeadLines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = "a8fc383efe21428a902d3479d814f9de"
    ): Observable<NewsResponse>

}