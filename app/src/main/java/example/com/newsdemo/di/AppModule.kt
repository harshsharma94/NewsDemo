package example.com.newsdemo.di

import android.app.Application
import dagger.Module
import dagger.Provides
import example.com.newsdemo.NewsDemoApplication
import javax.inject.Singleton

@Module
class AppModule(val app: NewsDemoApplication) {

    @Singleton
    @Provides
    fun provideApp(): Application {
        return app
    }
}