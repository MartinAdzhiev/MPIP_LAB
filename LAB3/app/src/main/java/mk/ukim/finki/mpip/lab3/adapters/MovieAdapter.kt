package mk.ukim.finki.mpip.lab3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mk.ukim.finki.mpip.lab3.R
import mk.ukim.finki.mpip.lab3.domain.movie.model.Movie

class MovieAdapter(private val movies: ArrayList<Movie> = ArrayList<Movie>(),
    val listener: ClickListener)
    :RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

       inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private var imageView: ImageView = view.findViewById(R.id.movie_image)
            private var title: TextView = view.findViewById(R.id.movie_title)
            private var year: TextView = view.findViewById(R.id.movie_year)

            fun bind(movie:Movie) {
                Glide.with(imageView).load(movie.poster)
                    .centerCrop().placeholder(R.drawable.baseline_movie_24).into(imageView)
                title.text = movie.title
                year.text = movie.year

                itemView.setOnClickListener {
                    listener.onClick(movie.imdbID)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_movie, parent, false)

        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun updateMovies(newMovies: List<Movie>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(id: String)
    }
}