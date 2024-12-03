package com.example.activityweek6
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activityweek6.databinding.ActivityDifficultiesBinding

class Difficulties : AppCompatActivity() {

    // Declare a variable to hold the view binding object.
    // The binding object allows access to all the views in the layout.
    private lateinit var binding: ActivityDifficultiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize the binding object. It inflates (creates) the layout views and binds them to this activity.
        binding = ActivityDifficultiesBinding.inflate(layoutInflater) //access to my components
        setContentView(binding.root) // Set the activity's content to the layout bound by the binding object.

        // Set up the action for the "Create Account" button when clicked.
        //val sharedPreferences: SharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE)
        //val editor : SharedPreferences.Editor = sharedPreferences.edit()

        // Set up the action for the "Back to Main" button when clicked.
        binding.btnEasy.setOnClickListener {
            val intent = Intent(this, Level::class.java)
             intent.putExtra("difficulty" , "Easy")
            startActivity(intent) // Start the MainActivity.
        }
        binding.btnMedium.setOnClickListener {
            val intent = Intent(this, Level::class.java)
            intent.putExtra("difficulty" , "Medium")
            startActivity(intent) // Start the MainActivity.
        }
        binding.btnIntermediate.setOnClickListener {
            val intent = Intent(this, Level::class.java)
            intent.putExtra("difficulty" , "Intermediate")
            startActivity(intent) // Start the MainActivity.
        }
        binding.btnAdvanced.setOnClickListener {
            val intent = Intent(this, Level::class.java)
            intent.putExtra("difficulty" , "Advanced")
            startActivity(intent) // Start the MainActivity.
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}