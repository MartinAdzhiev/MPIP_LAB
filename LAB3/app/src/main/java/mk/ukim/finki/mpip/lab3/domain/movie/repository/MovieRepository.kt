package mk.ukim.finki.mpip.lab3.domain.movie.repository

import mk.ukim.finki.mpip.lab3.domain.movie.LocalMovieDataSource
import mk.ukim.finki.mpip.lab3.domain.movie.RemoteMovieDataSource
import mk.ukim.finki.mpip.lab3.domain.movie.model.Movie

class MovieRepository(
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val localMovieDataSource: LocalMovieDataSource
) {

    suspend fun queryMovies(query: String): Movie {
        return remoteMovieDataSource.search(query).apply { insertMovie(this) }
    }

    suspend fun insertMovie(movie: Movie) {
        localMovieDataSource.insert(movie)
    }

    suspend fun getAllMovies(): List<Movie> {
        return localMovieDataSource.getAll()
    }

    suspend fun getMovieById(id: String): Movie {
        return localMovieDataSource.getById(id)
    }
}