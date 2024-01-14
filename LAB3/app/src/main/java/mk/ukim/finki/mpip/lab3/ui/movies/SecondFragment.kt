package mk.ukim.finki.mpip.lab3.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.room.util.newStringBuilder
import com.bumptech.glide.Glide
import mk.ukim.finki.mpip.lab3.R
import mk.ukim.finki.mpip.lab3.databinding.FragmentSecondBinding
import mk.ukim.finki.mpip.lab3.viewmodels.MovieDetailsViewModel

class SecondFragment : Fragment(R.layout.fragment_second) {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSecondBinding.bind(view)

        movieDetailsViewModel.getMovieDetailsLiveData().observe(viewLifecycleOwner) {

            Glide.with(binding.movieDetailImage).load(it.poster)
                .centerCrop()
                .placeholder(R.drawable.baseline_movie_24).into(binding.movieDetailImage)

            binding.movieDetailTitleAndYear.text = it.title + " " + "(${it.year})"
            binding.movieDetailImdb.text = newStringBuilder().append(getString(R.string.imbd)).append(it.imdbID)
            binding.movieDetailRuntime.text = newStringBuilder().append(getString(R.string.runtime)).append(it.runtime)
            binding.movieDetailGenre.text = newStringBuilder().append(getString(R.string.genre)).append(it.genre)
            binding.movieDetailDirector.text = newStringBuilder().append(getString(R.string.director)).append(it.director)
            binding.movieDetailActors.text = newStringBuilder().append(getString(R.string.actors)).append(it.actors)
            binding.movieDetailPlot.text = newStringBuilder().append(getString(R.string.plot)).append(it.plot)
            binding.movieDetailCountry.text = newStringBuilder().append(getString(R.string.country)).append(it.country)
            binding.movieDetailLanguage.text = newStringBuilder().append(getString(R.string.language)).append(it.language)
        }
    }
}