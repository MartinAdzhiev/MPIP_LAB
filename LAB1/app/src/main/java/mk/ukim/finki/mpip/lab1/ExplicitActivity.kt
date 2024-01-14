package mk.ukim.finki.mpip.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ExplicitActivity : AppCompatActivity() {

    private lateinit var editTextInput: EditText
    private lateinit var btnVoRed: Button
    private lateinit var btnOtkazi: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        editTextInput = findViewById(R.id.editTextInput)
        btnVoRed = findViewById(R.id.btnVoRed)
        btnOtkazi = findViewById(R.id.btnOtkazi)

        btnVoRed.setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra("response", editTextInput.text.toString()))
            finish()
        }

        btnOtkazi.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}