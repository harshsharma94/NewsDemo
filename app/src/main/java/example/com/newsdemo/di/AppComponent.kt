package example.com.newsdemo.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import example.com.newsdemo.NewsDemoApplication
import example.com.newsdemo.data.Repository
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, NetworkModule::class))
interface AppComponent {

    fun repository(): Repository

    fun inject(app: NewsDemoApplication)
}