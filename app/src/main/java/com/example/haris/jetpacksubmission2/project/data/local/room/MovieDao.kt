package com.example.haris.jetpacksubmission2.project.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.haris.jetpacksubmission2.project.data.local.entity.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteMovie(movie: Movie)

    @Update
    fun updateFavoriteMovie(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("SELECT * FROM Movie ORDER BY id ASC")
    fun getAllFavoriteMovie(): LiveData<List<Movie>>

}