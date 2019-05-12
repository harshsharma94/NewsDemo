package example.com.newsdemo.ui.news

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import example.com.newsdemo.R
import example.com.newsdemo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_news_list.*
import kotlinx.android.synthetic.main.content_news_list.*
import javax.inject.Inject

class NewsListActivity : BaseActivity(), NewsListView {

    private val adapter = NewsListAdapter()

    @Inject
    lateinit var presenter: NewsListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        setSupportActionBar(toolbar)


        getActivityComponent()?.inject(this)

        initView()
        initPresenter()
    }

    private fun initPresenter() {
        presenter.setView(this)
    }

    private fun initView() {
        rvNewsList.apply {
            adapter = this@NewsListActivity.adapter
            layoutManager = LinearLayoutManager(this@NewsListActivity)
            val spaceHeight = resources.getDimensionPixelSize(R.dimen.grid_2)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect, view: View,
                    parent: RecyclerView, state: RecyclerView.State
                ) {
                    with(outRect) {
                        if (parent.getChildAdapterPosition(view) == 0) {
                            top = spaceHeight
                        }
                        left = spaceHeight
                        right = spaceHeight
                        bottom = spaceHeight
                    }
                }

            })
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun setItems(items: List<NewsListAdapter.ItemType>) {
        adapter.setItems(items)
    }

    override fun showLoadingView(isLoading: Boolean) {
        //TODO: SwipeRefresh
    }
}
