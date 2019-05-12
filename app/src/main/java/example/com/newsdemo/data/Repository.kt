package example.com.newsdemo.data

import example.com.newsdemo.models.NewsResponse
import io.reactivex.Observable

interface Repository {

    fun getHeadLines(country: String): Observable<NewsResponse>

}