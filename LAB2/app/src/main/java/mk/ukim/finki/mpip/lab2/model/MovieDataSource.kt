package mk.ukim.finki.mpip.lab2.model

interface MovieDataSource {

    fun getAll(): List<Movie>

    fun add(movie: Movie): List<Movie>
}