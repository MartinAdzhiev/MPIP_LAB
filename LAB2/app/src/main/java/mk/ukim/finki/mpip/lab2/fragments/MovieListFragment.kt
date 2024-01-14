package mk.ukim.finki.mpip.lab2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.mpip.lab2.R
import mk.ukim.finki.mpip.lab2.adapters.MovieAdapter
import mk.ukim.finki.mpip.lab2.databinding.FragmentMovieListBinding
import mk.ukim.finki.mpip.lab2.viewModels.MovieDetailsViewModel
import mk.ukim.finki.mpip.lab2.viewModels.MoviesViewModel
import mk.ukim.finki.mpip.lab2.viewModels.MoviesViewModelFactory

class MovieListFragment : Fragment(R.layout.fragment_movie_list), MovieAdapter.ClickListener {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!


    private lateinit var moviesViewModel: MoviesViewModel

    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels {
        MoviesViewModelFactory(
            requireContext()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieListBinding.bind(view)

        val viewModelFactory = MoviesViewModelFactory(requireContext())
        moviesViewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]

        var adapter: MovieAdapter = MovieAdapter(ArrayList(), this)
        binding.list.adapter = adapter

        moviesViewModel.getMoviesLiveData().observe(viewLifecycleOwner) {
            adapter.updateMovies(it)
        }

        moviesViewModel.getAll()

        binding.addButton.setOnClickListener{
            AddNewMovieDialogFragment().show(childFragmentManager, "add-movie-dialog")
        }

    }

    override fun onClick(id: String) {

        movieDetailsViewModel.getMovieDetails(id)

        parentFragmentManager.commit {
            replace(R.id.fragment_container_view, MovieDetailsFragment())
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}