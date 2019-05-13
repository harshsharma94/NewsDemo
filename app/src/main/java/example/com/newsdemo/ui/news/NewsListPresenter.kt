package example.com.newsdemo.ui.news

import example.com.newsdemo.base.BasePresenter

interface NewsListPresenter : BasePresenter {
    fun refresh()
    fun setView(view: NewsListView)//TODO: Generic
    fun onNewsClick(position: Int)
}