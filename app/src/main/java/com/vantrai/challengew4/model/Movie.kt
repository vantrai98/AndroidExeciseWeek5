package com.vantrai.challengew4.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "popularity")
    val popularity: Float,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int,
    @ColumnInfo(name = "video")
    val video: Boolean,
    @ColumnInfo(name = "poster_path")
    val poster_path: String?,
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String?,
    @ColumnInfo(name = "original_language")
    val original_language: String?,
    @ColumnInfo(name = "original_title")
    val original_title: String?,
    @ColumnInfo(name = "genre_idsval")
    val genre_idsval: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "vote_average")
    val vote_average: Float,
    @ColumnInfo(name = "release_date")
    val release_date: String?,
    @ColumnInfo(name = "overview")
    val overview: String?
) : Parcelable {
}