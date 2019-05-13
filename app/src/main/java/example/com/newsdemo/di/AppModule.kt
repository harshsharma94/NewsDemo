package example.com.newsdemo.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import example.com.newsdemo.NewsDemoApplication
import example.com.newsdemo.data.ImageHandler
import example.com.newsdemo.data.ImageHandlerImpl
import example.com.newsdemo.data.NewsDao
import example.com.newsdemo.data.NewsDatabase
import javax.inject.Singleton

@Module
class AppModule(val app: NewsDemoApplication) {

    @Singleton
    @Provides
    fun provideApp(): Application {
        return app
    }

    @Singleton
    @Provides
    fun context(): Context {
        return app
    }

    @Singleton
    @Provides
    fun providesImageHandler(imageHandler: ImageHandlerImpl): ImageHandler {
        return imageHandler
    }

    @Singleton
    @Provides
    fun providesNewsDatabase(app: Application): NewsDatabase {
        return Room.databaseBuilder(
            app,
            NewsDatabase::class.java,
            "news-db"
        ).build()
    }

    @Singleton
    @Provides
    fun providesNewsDao(newsDatabase: NewsDatabase): NewsDao {
        return newsDatabase.newsDao()
    }
}