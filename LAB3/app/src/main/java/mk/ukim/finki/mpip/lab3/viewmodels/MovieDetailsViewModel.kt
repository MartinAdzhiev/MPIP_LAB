package mk.ukim.finki.mpip.lab3.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mk.ukim.finki.mpip.lab3.domain.movie.model.Movie
import mk.ukim.finki.mpip.lab3.domain.movie.repository.MovieRepository

class MovieDetailsViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()

    fun getMovieDetailsLiveData():LiveData<Movie> = _movie

    fun getMovieDetails(id:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = movieRepository.getMovieById(id)
            _movie.postValue(movie)
        }
    }

}