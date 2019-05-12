package example.com.newsdemo.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import example.com.newsdemo.NewsDemoApplication
import example.com.newsdemo.data.ImageHandler
import example.com.newsdemo.data.ImageHandlerImpl
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
}