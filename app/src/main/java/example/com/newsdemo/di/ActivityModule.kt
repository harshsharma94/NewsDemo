package example.com.newsdemo.di

import dagger.Module
import dagger.Provides
import example.com.newsdemo.ui.news.NewsListPresenter
import example.com.newsdemo.ui.news.NewsListPresenterImpl

@Module
class ActivityModule {

    @PerActivity
    @Provides
    fun providesNewsListPresenter(newsListPresenter: NewsListPresenterImpl): NewsListPresenter {
        return newsListPresenter
    }

}