package example.com.newsdemo.ui.news

import example.com.newsdemo.base.BaseView

interface NewsListView : BaseView {
    fun setItems(items: List<NewsListAdapter.ItemType>)
    fun launchNewsDetail(url: String)
}