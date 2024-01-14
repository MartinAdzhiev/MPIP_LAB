package mk.ukim.finki.mpip.lab2.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import mk.ukim.finki.mpip.lab2.R
import mk.ukim.finki.mpip.lab2.databinding.FragmentMovieDetailsBinding
import mk.ukim.finki.mpip.lab2.viewModels.MovieDetailsViewModel

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieDetailsBinding.bind(view)

        movieDetailsViewModel.getMovieDetailsLiveData().observe(viewLifecycleOwner) {
            binding.movieIdDetailed.text = "ID: ${it.id.toString()}"
            binding.movieTitleDetailed.text = "Title: ${it.title}"
            binding.movieDescriptionDetailed.text = "Description: ${it.description}"
            binding.movieDirectorDetailed.text = "Director: ${it.director}"
            binding.movieActorsDetailed.text = "Actors ${it.actors.toString()}"
        }
    }
}