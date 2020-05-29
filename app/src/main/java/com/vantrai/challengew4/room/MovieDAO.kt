package com.vantrai.challengew4.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.vantrai.challengew4.model.Movie

@Dao
interface MovieDAO {
    @Query("SELECT * FROM movie")
    fun getAll(): List<Movie>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun findById(id: Int): Movie

    @Insert
    fun insertAll(vararg movie: Movie): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: Movie): Long

    @Delete
    fun delete(obj: Movie)

    @Update
    fun update(obj: Movie)

    @Query("DELETE FROM movie")
    fun deleteAll()
}