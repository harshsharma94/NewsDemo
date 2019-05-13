package example.com.newsdemo.ui.news

import example.com.newsdemo.base.BasePresenterImpl
import example.com.newsdemo.data.Repository
import example.com.newsdemo.models.Articles
import example.com.newsdemo.models.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsListPresenterImpl @Inject constructor(val repository: Repository) : BasePresenterImpl(), NewsListPresenter {
    private lateinit var view: NewsListView//TODO: Generic

    companion object {
        const val LARGE_ITEM_INDEX = 3
    }

    private val articles = mutableListOf<Articles>()

    override fun refresh() {
        loadData()
    }

    override fun loadData() {
        val disposable = repository.getHeadLines(Country.IN.name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> handleResponse(response.articles) },
                { _ -> run {} }
            )
        addDisposable(disposable)
    }

    override fun subscribe() {
        super.subscribe()
        refresh()
    }

    private fun handleResponse(articles: List<Articles>) {
        val itemTypes = mutableListOf<NewsListAdapter.ItemType>()
        this.articles.addAll(articles)

        for ((i, article) in articles.withIndex()) {
            with(article) {
                if (i % LARGE_ITEM_INDEX == 0) {
                    itemTypes.add(
                        NewsListAdapter.LargeNewsItem(
                            urlToImage, title,
                            description, author.orEmpty()
                        )
                    )
                } else {
                    itemTypes.add(
                        NewsListAdapter.SmallNewsItem(
                            urlToImage, title,
                            description, author.orEmpty()
                        )
                    )
                }
            }
        }

        view.setItems(itemTypes)
    }

    override fun setView(view: NewsListView) {
        this.view = view
    }

    override fun onNewsClick(position: Int) {
        view.launchNewsDetail(articles[position].url)
    }
}