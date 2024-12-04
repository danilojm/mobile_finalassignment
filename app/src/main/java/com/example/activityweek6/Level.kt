package com.example.activityweek6

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.activityweek6.databinding.ActivityLevelBinding

class Level : AppCompatActivity() {
    private lateinit var binding: ActivityLevelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val difficulty = intent.getStringExtra("difficulty") ?: "Easy" // Default to "Easy" if null
        binding.textLevel.text = difficulty

        // Dynamically set the image based on difficulty
        val imageResourceName = difficulty.lowercase() // Convert to lowercase to match resource names
        val imageResId = resources.getIdentifier(imageResourceName, "drawable", packageName)

        if (imageResId != 0) { // If the resource is found
            binding.imageView.setImageResource(imageResId)
        } else {
            // Optional: Set a default image if the resource is not found
            binding.imageView.setImageResource(R.drawable.default_image)
        }

        binding.btnBackToDifficulty.setOnClickListener {
            val intent = Intent(this, Difficulties::class.java)
            startActivity(intent)
        }
    }
}
