package mk.ukim.finki.mpip.lab3.domain.movie.room

import mk.ukim.finki.mpip.lab3.domain.movie.LocalMovieDataSource
import mk.ukim.finki.mpip.lab3.domain.movie.model.Movie

class RoomMovieDataSource(private val movieDao: MovieDao) : LocalMovieDataSource {
    override suspend fun insert(movie: Movie) {
        movieDao.insert(movie)
    }

    override suspend fun getAll(): List<Movie> {
        return movieDao.getAll()
    }

    override suspend fun getById(id: String): Movie {
        return movieDao.getById(id)
    }
}