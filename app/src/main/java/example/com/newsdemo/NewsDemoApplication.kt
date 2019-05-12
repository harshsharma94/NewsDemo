package example.com.newsdemo

import android.app.Application
import example.com.newsdemo.di.AppComponent
import example.com.newsdemo.di.AppModule
import example.com.newsdemo.di.DaggerAppComponent
import example.com.newsdemo.di.NetworkModule
import ren.yale.android.cachewebviewlib.WebViewCacheInterceptor
import ren.yale.android.cachewebviewlib.WebViewCacheInterceptorInst


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

        WebViewCacheInterceptorInst.getInstance().init(WebViewCacheInterceptor.Builder(this))
    }
}