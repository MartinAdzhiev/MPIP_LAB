package mk.ukim.finki.mpip.lab3.domain.movie

import mk.ukim.finki.mpip.lab3.domain.movie.model.Movie

interface RemoteMovieDataSource {

    suspend fun search(query:String) : Movie
}