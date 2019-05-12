package example.com.newsdemo.ui.news.viewholder

import android.view.View
import android.view.ViewGroup
import example.com.newsdemo.R
import example.com.newsdemo.base.BaseViewHolder
import example.com.newsdemo.ui.news.NewsListAdapter
import kotlinx.android.synthetic.main.item_small_news.*

class SmallNewsViewHolder(parent: ViewGroup) :
    BaseViewHolder<NewsListAdapter.SmallNewsItem>(R.layout.item_small_news, parent) {
    override fun bind(data: NewsListAdapter.SmallNewsItem) {
        with(data) {
            textTitleSmall.setText(title)
            textDescSmall.setText(desc)

            textAuthorSmall.apply {
                if (authorName.isNotEmpty()) {
                    visibility = View.VISIBLE
                    setText(authorName)
                } else {
                    visibility = View.GONE
                }
            }
        }
    }
}