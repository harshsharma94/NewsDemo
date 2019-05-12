package example.com.newsdemo.ui.news

import android.view.ViewGroup
import example.com.newsdemo.base.BaseAdapter
import example.com.newsdemo.base.BaseViewHolder
import example.com.newsdemo.ui.news.viewholder.LargeNewsViewHolder
import example.com.newsdemo.ui.news.viewholder.SmallNewsViewHolder

class NewsListAdapter : BaseAdapter<NewsListAdapter.ItemType>() {

    fun setItems(items: List<ItemType>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_SMALL_NEWS -> SmallNewsViewHolder(parent)
            TYPE_LARGE_NEWS -> LargeNewsViewHolder(parent)
            else -> throw IllegalStateException("Wrong Item Type")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBind(vh: BaseViewHolder<*>, position: Int) {
        val item = getItem(position)
        when (item) {
            is SmallNewsItem -> (vh as SmallNewsViewHolder).bind(item)
            is LargeNewsItem -> (vh as LargeNewsViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getType()
    }

    private fun getItem(pos: Int): ItemType {
        return items[pos]
    }

    interface ItemType {
        fun getType(): Int
    }

    data class SmallNewsItem(
        val imgUrl: String, val title: String,
        val desc: String, val authorName: String
    ) : ItemType {

        override fun getType(): Int {
            return TYPE_SMALL_NEWS
        }
    }

    data class LargeNewsItem(
        val imgUrl: String, val title: String,
        val desc: String, val authorName: String
    ) :
        ItemType {

        override fun getType(): Int {
            return TYPE_LARGE_NEWS
        }
    }

    companion object {
        const val TYPE_SMALL_NEWS = 1
        const val TYPE_LARGE_NEWS = 2
    }
}
