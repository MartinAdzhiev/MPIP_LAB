package mk.ukim.finki.mpip.lab1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var textViewResultFromExplicitIntent: TextView
    private lateinit var btnExplicitActivity: Button
    private lateinit var btnImplicitActivity: Button
    private lateinit var btnChooseImage: Button

    private lateinit var imageUri: Uri

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if(it.resultCode == Activity.RESULT_OK)
            textViewResultFromExplicitIntent.text = it.data?.getStringExtra("response").toString()
    }

    private val imageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        imageUri = it.data?.data!!
        val showImage = Intent(Intent.ACTION_VIEW)
        showImage.setDataAndType(imageUri, "image/*")
        startActivity(showImage)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewResultFromExplicitIntent = findViewById(R.id.textViewResultFromExplicitIntent)
        btnExplicitActivity = findViewById(R.id.btnExplicitActivity)
        btnImplicitActivity = findViewById(R.id.btnImplicitActivity)
        btnChooseImage = findViewById(R.id.btnChooseImage)

        btnExplicitActivity.setOnClickListener {
            val explicitIntent = Intent(this, ExplicitActivity::class.java)
            launcher.launch(explicitIntent)
        }

        btnImplicitActivity.setOnClickListener {
            Intent().apply {
                action = "mk.ukim.finki.mpip.IMPLICIT_ACTION"
                type = "text/plain"
            }.let { i ->
                startActivity(i)
            }
        }

        btnChooseImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            imageLauncher.launch(intent)
        }

    }
}