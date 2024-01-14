package mk.ukim.finki.mpip.lab2.api

import mk.ukim.finki.mpip.lab2.model.Movie
import mk.ukim.finki.mpip.lab2.model.MovieDataSource

class FakeApiMovieDataSource(private val fakeApi: FakeApi): MovieDataSource {
    override fun getAll(): List<Movie> {
        return fakeApi.getAll()
    }

    override fun add(movie: Movie): List<Movie> {
        return fakeApi.add(movie)
    }
}