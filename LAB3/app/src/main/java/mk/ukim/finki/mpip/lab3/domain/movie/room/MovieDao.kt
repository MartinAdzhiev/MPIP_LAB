package mk.ukim.finki.mpip.lab3.domain.movie.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mk.ukim.finki.mpip.lab3.domain.movie.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>

    @Query("SELECT * FROM movie WHERE imdbID=:id")
    suspend fun getById(id:String): Movie
}