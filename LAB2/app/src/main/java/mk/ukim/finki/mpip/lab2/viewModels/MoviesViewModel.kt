package mk.ukim.finki.mpip.lab2.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mk.ukim.finki.mpip.lab2.model.Movie
import mk.ukim.finki.mpip.lab2.repository.MovieRepository

class MoviesViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val moviesLiveData = MutableLiveData<List<Movie>>()

    fun getMoviesLiveData(): LiveData<List<Movie>> = moviesLiveData

    fun getAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = movieRepository.getAll()
            moviesLiveData.postValue(movies)
        }
    }

//    fun addNewMovie(title: String, description:String, director:String, actors:ArrayList<String>) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val movies = movieRepository.add(title, description, director, actors)
//            moviesLiveData.postValue(movies)
//        }
    }
