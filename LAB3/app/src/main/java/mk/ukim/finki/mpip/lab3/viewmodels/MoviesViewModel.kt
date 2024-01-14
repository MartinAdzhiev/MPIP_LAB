package mk.ukim.finki.mpip.lab3.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mk.ukim.finki.mpip.lab3.domain.movie.model.Movie
import mk.ukim.finki.mpip.lab3.domain.movie.repository.MovieRepository

class MoviesViewModel(private val movieRepository: MovieRepository): ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()

    fun getMoviesLiveData(): LiveData<List<Movie>> = _movies

    fun search(query:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = movieRepository.queryMovies(query)
            _movies.postValue(listOf(movie))
        }
    }

    fun listAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = movieRepository.getAllMovies()
            _movies.postValue(movies)
        }
    }
}