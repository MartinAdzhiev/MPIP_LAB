package mk.ukim.finki.mpip.lab1

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ImplicitActivity : AppCompatActivity() {

    private lateinit var textViewImplicit: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        textViewImplicit = findViewById(R.id.textViewImplicit)

        val packageManager = packageManager
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val list = packageManager.queryIntentActivities(intent, 0)
        val builder = StringBuilder()

        for (r in list) {
            builder.append(r.activityInfo.name)
            builder.append("\n")
        }

        textViewImplicit.text = builder.toString()
    }
}