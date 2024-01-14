package mk.ukim.finki.mpip.lab3.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.mpip.lab3.domain.movie.repository.MovieRepository
import mk.ukim.finki.mpip.lab3.domain.movie.retrofit.MovieDbApiProvider
import mk.ukim.finki.mpip.lab3.domain.movie.retrofit.RetrofitMovieDataSource
import mk.ukim.finki.mpip.lab3.domain.movie.room.AppDatabase
import mk.ukim.finki.mpip.lab3.domain.movie.room.RoomMovieDataSource

class MoviesViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java)
            .newInstance(MovieRepository(RetrofitMovieDataSource(MovieDbApiProvider.getMovieDbApi()),
                RoomMovieDataSource(AppDatabase.getDatabase(context).movieDao())))
    }
}