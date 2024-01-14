package mk.ukim.finki.mpip.lab2.fragments

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import mk.ukim.finki.mpip.lab2.R


class AddNewMovieDialogFragment : DialogFragment() {

    interface AddMovieDialogListener {
        fun onDialogPositiveClick(
            title: String,
            description: String,
            director: String,
            actors: ArrayList<String>
        )

        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    private lateinit var listener: AddMovieDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.add_movie_dialog, null);
            val editMovieTitle: EditText = view.findViewById(R.id.edit_title)
            val editMovieDescription: EditText = view.findViewById(R.id.edit_description)
            val editMovieDirector: EditText = view.findViewById(R.id.edit_director)
            val editMovieActors: EditText = view.findViewById(R.id.edit_actors)

            builder.setView(view)
                .setPositiveButton(R.string.add) { _, _ ->
                    listener.onDialogPositiveClick(
                        editMovieTitle.text.toString(),
                        editMovieDescription.text.toString(),
                        editMovieDirector.text.toString(),
                        editMovieActors.text.toString().split(" ").toMutableList()
                            .toCollection(ArrayList())
                    )
                }
                .setNegativeButton(
                    R.string.cancel,
                    { _, _ ->
                        listener.onDialogNegativeClick(this)
                    })

            builder.create()
        } ?: throw IllegalStateException("Activity can not be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as AddMovieDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("${context.toString()} must implement AddMovieDialogListener.")
        }
    }

}