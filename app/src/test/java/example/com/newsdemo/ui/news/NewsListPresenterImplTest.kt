package example.com.newsdemo.ui.news

import com.nhaarman.mockitokotlin2.*
import example.com.newsdemo.data.Repository
import example.com.newsdemo.models.Articles
import example.com.newsdemo.models.Country
import example.com.newsdemo.models.NewsResponse
import example.com.newsdemo.models.Source
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NewsListPresenterImplTest {

    private lateinit var presenter: NewsListPresenter

    private val view: NewsListView = mock()

    private val repository: Repository = mock()

    private val argumentCaptor = argumentCaptor<List<NewsListAdapter.ItemType>>()

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        presenter = NewsListPresenterImpl(repository)
        presenter.setView(view)
    }

    @Test
    fun refresh() {
        val articles = mutableListOf<Articles>()
        getArticles(articles)
        whenever(repository.getHeadLines(Country.IN.name))
            .thenReturn(Observable.just(articles).map { t -> NewsResponse("ok", t.size, t) })

        presenter.refresh()

        //verify(view, times(2)).showLoadingView(any())
        verify(view).setItems(argumentCaptor.capture())

        assertEquals(argumentCaptor.firstValue.size, 4)
        assert(argumentCaptor.firstValue[0] is NewsListAdapter.LargeNewsItem)
        assert(argumentCaptor.firstValue[1] is NewsListAdapter.SmallNewsItem)
        assert(argumentCaptor.firstValue[2] is NewsListAdapter.SmallNewsItem)
        assert(argumentCaptor.firstValue[3] is NewsListAdapter.LargeNewsItem)

        verifyNoMoreInteractions(view)
    }

    private fun getArticles(items: MutableList<Articles>) {
        for (i in 0..3) {
            items.add(
                Articles(
                    i, null, null, "",
                    "", Source(null, ""), "News Title", "", ""
                )
            )
        }
    }
}