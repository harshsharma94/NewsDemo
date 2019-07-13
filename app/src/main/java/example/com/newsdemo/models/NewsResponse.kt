package example.com.newsdemo.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

data class NewsResponse(val status: String, val totalResults: Int, val articles: List<Articles>)

@Entity(tableName = "articles_table")
data class Articles(
    @PrimaryKey(autoGenerate = true)
    var uid: Int,

    val author: String?,
    val content: String?,
    val description: String,
    @ColumnInfo(name = "published_at")
    val publishedAt: String,
    @Embedded
    val source: Source,
    val title: String,
    val url: String,
    @ColumnInfo(name = "url_to_image")
    val urlToImage: String
)

data class Source(
    val id: String?,
    val name: String
)

enum class Country(name: String) {
    US("us"),
    IN("in")
}

