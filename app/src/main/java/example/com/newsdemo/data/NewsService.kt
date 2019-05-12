package example.com.newsdemo.data

import example.com.newsdemo.models.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsService {

    @GET("/v2/top-headlines")
    @Headers
    fun getHeadLines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = "7a2031e5a9534109bcc95105115d601a"
    ): Observable<NewsResponse>

}