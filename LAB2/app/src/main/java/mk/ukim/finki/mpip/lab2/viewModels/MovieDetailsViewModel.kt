package mk.ukim.finki.mpip.lab2.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mk.ukim.finki.mpip.lab2.model.Movie
import mk.ukim.finki.mpip.lab2.repository.MovieRepository

class MovieDetailsViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val movieDetailsLiveData = MutableLiveData<Movie>()

    fun getMovieDetailsLiveData(): LiveData<Movie> = movieDetailsLiveData

    fun getMovieDetails(id: String) {
        viewModelScope.launch {
            val movie = movieRepository.getById(id)
            movieDetailsLiveData.postValue(movie)
        }
    }
}