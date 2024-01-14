package mk.ukim.finki.mpip.lab3.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.mpip.lab3.R
import mk.ukim.finki.mpip.lab3.adapters.MovieAdapter
import mk.ukim.finki.mpip.lab3.databinding.FragmentFirstBinding
import mk.ukim.finki.mpip.lab3.domain.movie.model.Movie
import mk.ukim.finki.mpip.lab3.viewmodels.MovieDetailsViewModel
import mk.ukim.finki.mpip.lab3.viewmodels.MoviesViewModel
import mk.ukim.finki.mpip.lab3.viewmodels.MoviesViewModelFactory


class FirstFragment : Fragment(R.layout.fragment_first), MovieAdapter.ClickListener {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var moviesViewModel: MoviesViewModel

    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels {
        MoviesViewModelFactory(
            requireContext()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFirstBinding.bind(view)

        val viewModelFactory = MoviesViewModelFactory(requireContext())
        moviesViewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]

        var adapter: MovieAdapter = MovieAdapter(ArrayList<Movie>(), this)
        binding.list.adapter = adapter

        moviesViewModel.getMoviesLiveData().observe(viewLifecycleOwner) {
            adapter.updateMovies(it)
        }

        binding.nextButton.setOnClickListener{
            moviesViewModel.search(binding.editQuery.text.toString())
        }

        moviesViewModel.listAll()
    }

    override fun onClick(id: String) {
        movieDetailsViewModel.getMovieDetails(id)

        parentFragmentManager.commit {
            replace(R.id.fragmentView, SecondFragment())
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}