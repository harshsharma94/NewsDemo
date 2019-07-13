package example.com.newsdemo.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import example.com.newsdemo.models.Articles
import io.reactivex.Flowable

@Dao
interface NewsDao {

    @Query("SELECT * FROM articles_table LIMIT :limit OFFSET :offset")
    fun getArticles(limit: Int, offset: Int = 0): Flowable<List<Articles>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<Articles>): LongArray

    @Query("DELETE FROM articles_table")
    fun deleteAll()

}