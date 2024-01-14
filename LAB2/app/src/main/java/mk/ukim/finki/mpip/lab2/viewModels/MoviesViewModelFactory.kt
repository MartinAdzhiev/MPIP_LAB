package mk.ukim.finki.mpip.lab2.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.mpip.lab2.api.FakeApiMovieDataSource
import mk.ukim.finki.mpip.lab2.api.FakeApiProvider
import mk.ukim.finki.mpip.lab2.repository.MovieRepository

class MoviesViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java)
            .newInstance(MovieRepository(FakeApiMovieDataSource(FakeApiProvider.getFakeApi())))
    }
}