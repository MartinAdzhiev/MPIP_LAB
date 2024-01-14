package mk.ukim.finki.mpip.lab3.domain.movie

import mk.ukim.finki.mpip.lab3.domain.movie.model.Movie

interface LocalMovieDataSource {

    suspend fun insert(movie: Movie)

    suspend fun getAll(): List<Movie>

    suspend fun getById(id: String): Movie
}