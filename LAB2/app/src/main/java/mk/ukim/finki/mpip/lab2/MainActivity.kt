package mk.ukim.finki.mpip.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.mpip.lab2.api.FakeApiMovieDataSource
import mk.ukim.finki.mpip.lab2.api.FakeApiProvider
import mk.ukim.finki.mpip.lab2.fragments.AddNewMovieDialogFragment
import mk.ukim.finki.mpip.lab2.fragments.MovieListFragment
import mk.ukim.finki.mpip.lab2.model.Movie
import mk.ukim.finki.mpip.lab2.model.MovieDataSource
import mk.ukim.finki.mpip.lab2.repository.MovieRepository
import mk.ukim.finki.mpip.lab2.viewModels.MovieDetailsViewModel
import mk.ukim.finki.mpip.lab2.viewModels.MoviesViewModel
import mk.ukim.finki.mpip.lab2.viewModels.MoviesViewModelFactory

class MainActivity : AppCompatActivity(), AddNewMovieDialogFragment.AddMovieDialogListener {

//    private lateinit var moviesViewModel: MoviesViewModel

    private lateinit var repository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.fragment_container_view, MovieListFragment())
                setReorderingAllowed(true)
            }
        }
//        val viewModelFactory = MoviesViewModelFactory(requireContext())
//        moviesViewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]

        repository = MovieRepository(FakeApiMovieDataSource(FakeApiProvider.getFakeApi()))

    }


    override fun onDialogPositiveClick(
        title: String,
        description: String,
        director: String,
        actors: ArrayList<String>
    ) {
        repository.add(title, description, director, actors)

        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, MovieListFragment())
            setReorderingAllowed(true)
        }
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        dialog.dismiss()
    }
}