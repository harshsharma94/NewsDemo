package example.com.newsdemo

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import example.com.newsdemo.di.AppComponent
import example.com.newsdemo.di.AppModule
import example.com.newsdemo.di.DaggerAppComponent
import example.com.newsdemo.di.NetworkModule

class NewsDemoApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .networkModule(NetworkModule())
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}