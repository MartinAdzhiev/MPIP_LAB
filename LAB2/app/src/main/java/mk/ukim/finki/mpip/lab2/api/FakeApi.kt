package mk.ukim.finki.mpip.lab2.api

import mk.ukim.finki.mpip.lab2.model.Movie

class FakeApi {

    private val movies: MutableList<Movie> = ArrayList<Movie>()

    fun getAll(): List<Movie> {
        return movies
    }

    fun add(movie: Movie): List<Movie> {
        movies.add(movie)
        return this.getAll()
    }
}