package mk.ukim.finki.mpip.lab3.domain.movie.retrofit

import mk.ukim.finki.mpip.lab3.domain.movie.RemoteMovieDataSource
import mk.ukim.finki.mpip.lab3.domain.movie.model.Movie

class RetrofitMovieDataSource(private val movieDbApi: MovieDbApi) : RemoteMovieDataSource {
    override suspend fun search(query: String): Movie {
        val movieResponse = movieDbApi.search(query)
        val responseBody = movieResponse.body()

        if (movieResponse.isSuccessful && responseBody!=null) {
            return responseBody
        }
        throw Exception("Error")
    }
}