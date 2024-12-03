package com.example.activityweek6

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activityweek6.databinding.ActivityLevelBinding

class Level : AppCompatActivity() {
    private lateinit var binding: ActivityLevelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLevelBinding.inflate(layoutInflater)


        setContentView(binding.root)
        val difficulty=intent.getStringExtra("difficulty")
        binding.textLevel.text=difficulty

        binding.btnBackToDifficulty.setOnClickListener {
            val intent = Intent(this, Difficulties::class.java)
            startActivity(intent) // Start the MainActivity.
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}