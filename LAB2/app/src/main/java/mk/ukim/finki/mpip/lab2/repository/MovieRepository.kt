package mk.ukim.finki.mpip.lab2.repository

import mk.ukim.finki.mpip.lab2.model.Movie
import mk.ukim.finki.mpip.lab2.model.MovieDataSource

class MovieRepository(private val movieDataSource: MovieDataSource) {

     fun getAll(): List<Movie> {
        return movieDataSource.getAll()
    }

     fun getById(id: String): Movie {
        val movies = this.getAll()
        val movie = movies.find { movie -> movie.id.toString() == id }

        return movie!!
    }

    fun add(
        title: String,
        description: String,
        director: String,
        actors: ArrayList<String>
    ): List<Movie> {
        val movie = Movie(
            this.getAll().size + 1,
            title,
            description,
            director,
            actors
        )
        return movieDataSource.add(movie)
    }
}