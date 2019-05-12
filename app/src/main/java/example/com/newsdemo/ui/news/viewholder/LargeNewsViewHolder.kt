package example.com.newsdemo.ui.news.viewholder

import android.view.View
import android.view.ViewGroup
import example.com.newsdemo.R
import example.com.newsdemo.base.BaseViewHolder
import example.com.newsdemo.ui.news.NewsListAdapter
import kotlinx.android.synthetic.main.item_large_news.*

class LargeNewsViewHolder(parent: ViewGroup) :
    BaseViewHolder<NewsListAdapter.LargeNewsItem>(R.layout.item_large_news, parent) {
    override fun bind(data: NewsListAdapter.LargeNewsItem) {

        with(data) {
            textTitleLarge.setText(title)
            textDescLarge.setText(desc)

            textAuthorLarge.apply {
                if (authorName.isNotEmpty()) {
                    visibility = View.VISIBLE
                    setText(authorName)
                } else {
                    visibility = View.VISIBLE
                }
            }
        }
    }
}