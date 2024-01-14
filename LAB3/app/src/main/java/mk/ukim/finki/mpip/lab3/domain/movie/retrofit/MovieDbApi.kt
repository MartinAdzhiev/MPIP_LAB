package mk.ukim.finki.mpip.lab3.domain.movie.retrofit

import mk.ukim.finki.mpip.lab3.domain.movie.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApi {
    @GET("/")
    suspend fun search(@Query("t") query:String) : Response<Movie>
}