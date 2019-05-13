package example.com.newsdemo.data

import example.com.newsdemo.models.NewsResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    val newsService: NewsService,
    val newsDao: NewsDao
) : Repository {

    override fun getHeadLines(country: String): Observable<NewsResponse> {
        val articlesDB = newsDao.getArticles(20, 0)
            .filter { articles -> articles.isNotEmpty() }
            .map { articles -> NewsResponse("ok", articles.size, articles) }
            .subscribeOn(Schedulers.computation())


        val articlesApi = newsService.getHeadLines(country)
            .map { newsResponse ->

                Observable.create<Any> { subscriber ->
                    newsDao.insertAll(newsResponse.articles)
                    subscriber.onComplete()
                }.subscribeOn(Schedulers.computation()).subscribe()

                newsResponse
            }
            .subscribeOn(Schedulers.io())

        return articlesApi.onErrorResumeNext(articlesDB.toObservable())
    }

}