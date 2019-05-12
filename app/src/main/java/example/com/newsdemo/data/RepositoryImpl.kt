package example.com.newsdemo.data

import example.com.newsdemo.models.NewsResponse
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val newsService: NewsService) : Repository {

    override fun getHeadLines(country: String): Observable<NewsResponse> {
        return newsService.getHeadLines(country)
    }

}