package example.com.newsdemo.base

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import example.com.newsdemo.NewsDemoApplication
import example.com.newsdemo.di.ActivityComponent
import example.com.newsdemo.di.ActivityModule
import example.com.newsdemo.di.DaggerActivityComponent

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    protected fun getActivityComponent(): ActivityComponent? {
        return DaggerActivityComponent.builder()
            .appComponent((application as NewsDemoApplication).component)
            .activityModule(ActivityModule())
            .build()
    }

}