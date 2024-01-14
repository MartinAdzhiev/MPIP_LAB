package mk.ukim.finki.mpip.lab2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import mk.ukim.finki.mpip.lab2.R
import mk.ukim.finki.mpip.lab2.model.Movie

class MovieAdapter(
    private val movies: ArrayList<Movie> = ArrayList<Movie>(),
    val listener: ClickListener
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var movieId: TextView = view.findViewById(R.id.movie_id)
        private var title: TextView = view.findViewById(R.id.movie_title)
        private var director: TextView = view.findViewById(R.id.movie_director)

//        init {
//            view.setOnClickListener {
//                val position = adapterPosition
//                listener.onClick(position)
//            }
//        }

        fun bind(movie: Movie) {
            movieId.text = movie.id.toString()
            title.text = movie.title
            director.text = movie.director

            itemView.setOnClickListener {
                listener.onClick(movie.id.toString())
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

    fun updateMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)

        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(id: String)
    }

}