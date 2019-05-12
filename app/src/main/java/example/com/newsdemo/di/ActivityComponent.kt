package example.com.newsdemo.di

import dagger.Component
import example.com.newsdemo.ui.news.NewsListActivity

@PerActivity
@Component(modules = (arrayOf(ActivityModule::class)), dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {
    fun inject(newsListActivity: NewsListActivity)
}